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
   * ��ǰ ���
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/list.do", method = RequestMethod.GET)
  public ModelAndView listProc(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/list");  // /mpproduct/list.jsp
    mav.addObject("root", Utility.getRoot()); // /mpproduct //request�� �������� request.setAttribute�� ����

    //null �϶��� Setting
    if (dto.getNowPage() <1){
      dto.setNowPage(1);
    }
    
    int nowPage = dto.getNowPage(); //���� ������ ��ȣ�� 1����

 /*    //���� nowPage(���� ������)�� 0���� ���� // null�� �ƴϸ� �ٽ� �����ͼ� ����ȯ
    if (request.getAttribute("nowPage") != null) {
      nowPage= Integer.parseInt(request.getParameter("nowPage")); 
    }*/
    
    dto.setRecordPerPage(10);
    int recordPerPage = dto.getRecordPerPage(); // �������� ���ڵ� �� //dao�� dao.list�� �¾ƾ� ��...
 

    dto.setTotalRecord(mpproductDAO.count(dto)); 
    //System.out.println(">>>>>>>>>>>>>>>>>count:::::" + dto.getTotalRecord());    
    
    // ���� ��� => ȭ��ǰ��
    mav.addObject("searchName", mpproductDAO.distictNamelist());//�Ŵ� db�� �ٽ��� ����
    
    // ���� ��� => �Ǻ�Ÿ��
    mav.addObject("searchFtype", mpproductDAO.distictFtylelist());//�Ŵ� db�� �ٽ��� ����    
   
    // ���
    //mav.addObject("list", mpproductDAO.list(dto));//�Ŵ� db�� �ٽ��� ����
    mav.addObject("list", mpproductDAO.list(dto, nowPage, recordPerPage));//�Ŵ� db�� �ٽ��� ����
    //= request.setAttribute("list", dao.list(getMpgroupno()));//���� ���� �ǹ���...
    
    //mpgroupno�� �̿��Ͽ� �Խ��� ���� ����
    MpgroupDTO mpgroupDTO = mpgroupDAO.read(dto.getMpgroupno());
    // System.out.println(">>>>> mpgroupDTO.getMpgrname(): " + mpgroupDTO.getMpgrname());
    
    mav.addObject("mpgrname", mpgroupDTO.getMpgrname());
    mav.addObject("mpgroupno", mpgroupDTO.getMpgroupno());
    
    //�˻��� ���� �ʿ��� �� ����
    
    //mpproductDAO.read(dto.getProductno());
    //mav.addObject("mpproductno", dto.getProductno());    
    mav.addObject("MpproductDTO", dto); 
   
    
    return mav;
  }
 
  /**
   * ��ǰ ���
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
   * ��ǰ ��� ����
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
    // filename ���� ���� ����
    // --------------------------------------------------------
    // ���۵� ������ �ڵ� ����Ǿ� ����.
    MultipartFile filenameMF = dto.getFnameMF();

    // ������ ���۵� ���� ����
    String basePath = Utility.getRealPath(request, "/mpproduct/storage");
    String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath);

    dto.setFname(filename); // ���ϸ�
    // --------------------------------------------------------
 
    //if()
    //int mcount = checkAttrInt((MpproductDTO)request.getAttribute("mcount"), dto.getMcount());    
    //System.out.println(">>>>>>>>>>> mcount::::" + dto.getMcount());
    
    int cnt = mpproductDAO.create(dto);
    if (cnt == 1){
      mav.addObject("msg1", "��ǰ�� ����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");    
    }else{
      mav.addObject("msg1", "��ǰ ��Ͽ� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");    
      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");      
    }
    
    return mav;
  }
  
  /**
   * ��ǰ ��ȸ
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
   * ��ǰ ����
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
   * ��ǰ ���� ����
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/updateProduct.do", method=RequestMethod.POST)
  public ModelAndView updateProc(MpproductDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");   // /media/msgView.jsp 
    mav.addObject("root", Utility.getRoot()); // /media
    
    // ������ ��ϵ� ���� ���� ����
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    // --------------------------------------------------------
    // filename ���� ���� ����
    // --------------------------------------------------------
    // ���۵� ������ �ڵ� ����Ǿ� ����.
    MultipartFile fnameMF = dto.getFnameMF();
    
    //  ���� �����ϴ� ������ �ִٸ� �������� ������ ������ ����
    if(fnameMF.getSize() > 0){
      String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //����������
      Utility.deleteFile(basePath + "/" + oldDTO.getFname());
      
      // ������ ���۵� ���� ����
      String filename = UploadSaveManager.saveFileSpring30(fnameMF, basePath);

      dto.setFname(filename); // ���ϸ�
    }else{
      dto.setFname(oldDTO.getFname());
    }
    // --------------------------------------------------------
    
    int cnt = mpproductDAO.update(dto);
    if (cnt == 1){
      mav.addObject("msg1", "��ǰ�� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");    
    }else{
      mav.addObject("msg1", "��ǰ������ �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");    
      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getMpgroupno()+"';\">");      
    }
    
    return mav;
  }
  
  /**
   * ��ǰ ����
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
   * ��ǰ ���� ����
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/deleteProduct.do", method=RequestMethod.POST)
  public void  deleteProc(MpproductDTO dto, HttpServletRequest request, HttpServletResponse response){
    // ������ ��ϵ� ���� ���� ����
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //����������
    Utility.deleteFile(basePath + "/" + oldDTO.getFname());
    
    try{
      response.setContentType("text/html;charset=utf-8");  
      PrintWriter out =  response.getWriter();

      int cnt = mpproductDAO.delete(dto.getProductno());
      if (cnt == 1){
        out.write(dto.getProductno() + "�� ��ǰ�� �����߽��ϴ�.");
   
      }else{
        out.write(dto.getProductno() + "�� ��ǰ ������ �����߽��ϴ�.");      }
        
    }catch(Exception e){
      System.out.println(e.toString());
    }
    
  }

  /**
   * jQuery ȣ��, ��ȸ �� 
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
   * �԰��� ����
   * @param dto
   * @param request
   * @return
   */
  @RequestMapping(value="/mpproduct/countupdate.do", method=RequestMethod.POST)
  public void  countupdateProc(MpproductDTO dto, HttpServletRequest request, HttpServletResponse response){
    // ������ ��ϵ� ���� ���� ����
    MpproductDTO oldDTO = mpproductDAO.read(dto.getProductno());
    
    MultipartFile fnameMF = dto.getFnameMF();
    
    if(fnameMF.getSize() > 0){
      String basePath = Utility.getRealPath(request, "/mpproduct/storage"); //����������
      Utility.deleteFile(basePath + "/" + oldDTO.getFname());
      
      // ������ ���۵� ���� ����
      String filename = UploadSaveManager.saveFileSpring30(fnameMF, basePath);

      dto.setFname(filename); // ���ϸ�
    }else{
      dto.setFname(oldDTO.getFname());
    }
    
    try{
      response.setContentType("text/html;charset=utf-8");  
      PrintWriter out =  response.getWriter();

      int cnt = mpproductDAO.update(dto);
      if (cnt == 1){
        out.write(dto.getProductno() + "�� ��ǰ�� �԰��߽��ϴ�.");
   
      }else{
        out.write(dto.getProductno() + "�� ��ǰ �԰� �����߽��ϴ�.");      }
        
    }catch(Exception e){
      System.out.println(e.toString());
    }
    
  }
  


  
  
}
