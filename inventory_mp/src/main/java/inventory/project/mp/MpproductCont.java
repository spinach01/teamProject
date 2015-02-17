package inventory.project.mp;

import java.io.PrintWriter;
import java.util.ArrayList;

import inventory.project.group.MpgroupDAO;
import inventory.project.group.MpgroupDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import www.utility.UploadSaveManager;
import www.utility.Utility;

@Controller
public class MpproductCont {
 
  @Autowired
  private MpproductDAO mpproductDAO;
  @Autowired
  private MpgroupDAO mpgroupDAO;
  
  public MpproductCont(){
    System.out.println("MediaCont auto created...");
  } 
  
  /**
   * 상품 목록
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/list.do", method = RequestMethod.GET)
  public ModelAndView listProc(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/list");  // /mpproduct/list.jsp
    mav.addObject("root", Utility.getRoot()); // /mpproduct //request는 못쓰지만 request.setAttribute와 같음

    //null 일때만 Setting
    if (dto.getNowPage() <1){
      dto.setNowPage(1);
    }
    
    int nowPage = dto.getNowPage(); //시작 페이지 번호는 1부터

 /*    //최초 nowPage(현재 페이지)는 0부터 시작 // null이 아니면 다시 가져와서 형변환
    if (request.getAttribute("nowPage") != null) {
      nowPage= Integer.parseInt(request.getParameter("nowPage")); 
    }*/
    
    dto.setRecordPerPage(10);
    int recordPerPage = dto.getRecordPerPage(); // 페이지당 레코드 수 //dao의 dao.list와 맞아야 함...
 

    dto.setTotalRecord(mpproductDAO.count(dto)); 
    //System.out.println(">>>>>>>>>>>>>>>>>count:::::" + dto.getTotalRecord());    
    
    // 선택 목록 => 화장품명
    mav.addObject("searchName", mpproductDAO.distictNamelist());//거대 db의 핵심이 있음
    
    // 선택 목록 => 피부타입
    mav.addObject("searchFtype", mpproductDAO.distictFtylelist());//거대 db의 핵심이 있음    
   
    // 목록
    //mav.addObject("list", mpproductDAO.list(dto));//거대 db의 핵심이 있음
    mav.addObject("list", mpproductDAO.list(dto, nowPage, recordPerPage));//거대 db의 핵심이 있음
    //= request.setAttribute("list", dao.list(getMpgroupno()));//위와 같은 의미임...
    
    //mpgroupno를 이용하여 게시판 제목 추출
    MpgroupDTO mpgroupDTO = mpgroupDAO.read(dto.getMpgroupno());
    // System.out.println(">>>>> mpgroupDTO.getMpgrname(): " + mpgroupDTO.getMpgrname());
    
    mav.addObject("mpgrname", mpgroupDTO.getMpgrname());
    mav.addObject("mpgroupno", mpgroupDTO.getMpgroupno());
    
    //검색을 위해 필요한 값 추출
    
    //mpproductDAO.read(dto.getProductno());
    //mav.addObject("mpproductno", dto.getProductno());    
    mav.addObject("MpproductDTO", dto); 
   
    
    return mav;
  }
 
  /**
   * 상품 등록
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/createProduct.do", method=RequestMethod.GET)
  public ModelAndView create(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createProduct");   // /media/createForm.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    mav.addObject("mpgroupno", dto.getMpgroupno());
    // request.setAttribute("mediagroupno", dto.getMediagroupno());
    
    mav.addObject("mcount", dto.getMcount());
   
    return mav;
  }
  
  private int checkAttrInt(MpproductDTO mpproductDTO, int mcount) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * 상품 등록 실행
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/createProduct.do", method=RequestMethod.POST)
  public ModelAndView createProc(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");   // /mediagroup/msgView.jsp 
    mav.addObject("root", Utility.getRoot()); // /media

    // --------------------------------------------------------
    // filename 파일 전송 관련
    // --------------------------------------------------------
    // 전송된 파일이 자동 저장되어 있음.
    MultipartFile filenameMF = dto.getFnameMF();

    // 서버에 전송된 파일 저장
    String basePath = Utility.getRealPath(request, "/mpproduct/storage");
    String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath);

    dto.setFname(filename); // 파일명
    // --------------------------------------------------------
 
    //if()
    //int mcount = checkAttrInt((MpproductDTO)request.getAttribute("mcount"), dto.getMcount());    
    //System.out.println(">>>>>>>>>>> mcount::::" + dto.getMcount());
    
    int cnt = mpproductDAO.create(dto);
    if (cnt == 1){
      mav.addObject("msg1", "상품을 등록했습니다.");
      mav.addObject("link1", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");    
    }else{
      mav.addObject("msg1", "상품 등록에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");    
      mav.addObject("link2", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");      
    }
    
    return mav;
  }
  
  /**
   * 상품 조회
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/read.do", method=RequestMethod.GET)
  public ModelAndView read(MpproductDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/read");   // /media/list.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    mav.addObject("dto", mpproductDAO.read(dto.getProductno()));
    // request.setAttribute("list", dao.list(dto.getMediagroupno()));

    return mav;
  }
  
  /**
   * 상품 수정
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/updateProduct.do", method=RequestMethod.GET)
  public ModelAndView updateForm(MpproductDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/updateProduct");   // /media/createForm.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    dto = mpproductDAO.read(dto.getProductno());
    mav.addObject("dto", dto);
    // request.setAttribute("dto", dto);
    return mav;
  }
  
  /**
   * 상품 수정 실행
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/updateProduct.do", method=RequestMethod.POST)
  public ModelAndView updateProc(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");   // /media/msgView.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    // 기존의 등록된 파일 정보 추출
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    // --------------------------------------------------------
    // filename 파일 전송 관련
    // --------------------------------------------------------
    // 전송된 파일이 자동 저장되어 있음.
    MultipartFile fnameMF = dto.getFnameMF();
    
    //  새로 전송하는 파일이 있다면 기존파일 삭제후 새파일 전송
    if(fnameMF.getSize() > 0){
      String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //절대경로추출
      Utility.deleteFile(basePath + "/" + oldDTO.getFname());
      
      // 서버에 전송된 파일 저장
      String filename = UploadSaveManager.saveFileSpring30(fnameMF, basePath);

      dto.setFname(filename); // 파일명
    }else{
      dto.setFname(oldDTO.getFname());
    }
    // --------------------------------------------------------
    
    int cnt = mpproductDAO.update(dto);
    if (cnt == 1){
      mav.addObject("msg1", "상품을 수정했습니다.");
      mav.addObject("link1", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");    
    }else{
      mav.addObject("msg1", "상품수정에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");    
      mav.addObject("link2", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");      
    }
    
    return mav;
  }
  
  /**
   * 상품 삭제
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/deleteProduct.do", method=RequestMethod.GET)
  public ModelAndView deleteForm(MpproductDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/deleteProduct");   // /media/createForm.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    dto = mpproductDAO.read(dto.getProductno());
    mav.addObject("dto", dto);
    // request.setAttribute("dto", dto);
    return mav;
  }
  
  /**
   * 상품 삭제 실행
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/deleteProduct.do", method=RequestMethod.POST)
  public void  deleteProc(MpproductDTO dto, HttpServletRequest request, HttpServletResponse response){
    // 기존의 등록된 파일 정보 추출
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //절대경로추출
    Utility.deleteFile(basePath + "/" + oldDTO.getFname());
    
    try{
      response.setContentType("text/html;charset=utf-8");  
      PrintWriter out =  response.getWriter();

      int cnt = mpproductDAO.delete(dto.getProductno());
      if (cnt == 1){
        out.write(dto.getProductno() + "번 상품을 삭제했습니다.");
   
      }else{
        out.write(dto.getProductno() + "번 상품 삭제에 실패했습니다.");      }
        
    }catch(Exception e){
      System.out.println(e.toString());
    }
    
  }

  /**
   * jQuery 호출, 조회 폼 
   * @param dto
   * @param response
   */
   @RequestMapping(value="mpproduct/countread.do", method=RequestMethod.GET)
   public void countupdateForm(MpproductDTO dto, HttpServletResponse response){
     try{
       response.setContentType("text/html; charset=utf-8");
       PrintWriter out = response.getWriter();
       
       dto = mpproductDAO.read(dto.getProductno());  
       out.write(dto.getMcount());
       
     }catch(Exception e){
       System.out.println(e.toString());
     }
   }  
  
  /**
   * 입고물량 수정
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/countupdate.do", method=RequestMethod.POST)
  public void  countupdateProc(MpproductDTO dto, HttpServletRequest request, HttpServletResponse response){
    // 기존의 등록된 파일 정보 추출
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    MultipartFile fnameMF = dto.getFnameMF();
    
    if(fnameMF.getSize() > 0){
      String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //절대경로추출
      Utility.deleteFile(basePath + "/" + oldDTO.getFname());
      
      // 서버에 전송된 파일 저장
      String filename = UploadSaveManager.saveFileSpring30(fnameMF, basePath);

      dto.setFname(filename); // 파일명
    }else{
      dto.setFname(oldDTO.getFname());
    }
    
    try{
      response.setContentType("text/html;charset=utf-8");  
      PrintWriter out =  response.getWriter();

      int cnt = mpproductDAO.update(dto);
      if (cnt == 1){
        out.write(dto.getProductno() + "번 상품을 입고했습니다.");
   
      }else{
        out.write(dto.getProductno() + "번 상품 입고에 실패했습니다.");      }
        
    }catch(Exception e){
      System.out.println(e.toString());
    }
    
  }
  


  
  
}
