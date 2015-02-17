package inventory.project.mpexport;

import javax.servlet.http.HttpServletRequest;

import inventory.project.mp.MpproductDTO;
import inventory.project.mp.MpproductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;





import www.utility.UploadSaveManager;
import www.utility.Utility;

/**
 * @Controller : 접속자가 브라우저에서 URL을 입력하여 접근시 주소에 해당하는 매소드를 구현하고 
 *               데이터를 처리하여 JSP 페이지로 이동하는 기능을 함   
 *               1. 주소에 해당하는 메소드 실행
 *               2. DTO, DAO등의 데이타 처리
 *               3. JSP 페이지로 이동
 */
@Controller
public class MpexportCont {
  @Autowired
  private MpexportDAO mpexportDAO;
  
  @Autowired
  private MpproductDAO mpproductDAO;  
  
  public MpexportCont(){
    System.out.println("Mpexport auto created...");
  }
  
  /**
   * 상품 그룹 등록
   * @return
   */
  @RequestMapping(value="/mpproduct/createExport.do", method = RequestMethod.GET)
  public ModelAndView exoprtcreateForm(MpexportDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createExport"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() 호출됨.... ");
 
    MpproductDTO mpproductDTO =  mpproductDAO.read(dto.getProductno());
    mav.addObject("mpproductDTO", mpproductDTO); 
    
    dto.setExportgroupno(mpproductDTO.getMpgroupno()); 
    dto.setExportmcount(mpproductDTO.getMcount());
    //mav.addObject("mpexportDTO", dto);
    
    // product의 그룹번호 수량 가격 저장
    mav.addObject("mpexportmpgroupno",dto.getExportgroupno());
    mav.addObject("mpexportmpmcount",dto.getExportmcount());
    mav.addObject("mpexportmpmprice",dto.getExportmprice());

    // 출고 수량과 가격 String/int 오류 수정을 위한 값 저장
    mav.addObject("mpexportexcount",dto.getExcount());
    mav.addObject("mpexportexprice",dto.getExprice());
    
    return mav;
  }

  /**
   * 상품 그룹 실행
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/createExport.do", method = RequestMethod.POST)  
  public ModelAndView exportcreateProc(MpexportDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request는 못쓰지만 request.setAttribute와 같음 //절대경로 가져오기(msgview에 사용)
  
    int totalcount = (int)dto.getExportmcount() - (int)dto.getExcount();
    int totalprice = (int)dto.getExportmprice();
    
    MpproductDTO mpproductexportDTO =  mpproductDAO.read(dto.getProductno());
    
    
    System.out.println(">>>>>>>>>>>>>>>>>dto.getExportmcount() :::" +dto.getExportmcount() );
    System.out.println(">>>>>>>>>>>>>>>>>dto.getExcount()::::" +dto.getExcount() );
    
    int cnt = mpexportDAO.create(dto);

    if(cnt == 1){      

      if(totalcount > 0){
        mpproductexportDTO.setMcount(totalcount);
        mpproductDAO.update(mpproductexportDTO);     
      }
      else{
        totalcount=0;
        mpproductexportDTO.setMcount(totalcount);
        mpproductDAO.update(mpproductexportDTO);
      }// else일때는 팝업창으로 에러 표시 하는것 고려!             
      
      mav.addObject("msg1", "상품 출고를했습니다.");
      mav.addObject("link1", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getExportgroupno()+"';\">");   
    }else{
      mav.addObject("msg1", "상품 출고에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getExportgroupno()+"';\">"); 
    }
    
    return mav;
  }
 
}
