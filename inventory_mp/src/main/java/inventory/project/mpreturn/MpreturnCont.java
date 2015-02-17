package inventory.project.mpreturn;

import inventory.project.mp.MpproductDTO;
import inventory.project.mp.MpproductDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import www.utility.Utility;

/**
 * @Controller : 접속자가 브라우저에서 URL을 입력하여 접근시 주소에 해당하는 매소드를 구현하고 
 *               데이터를 처리하여 JSP 페이지로 이동하는 기능을 함   
 *               1. 주소에 해당하는 메소드 실행
 *               2. DTO, DAO등의 데이타 처리
 *               3. JSP 페이지로 이동
 */
@Controller
public class MpreturnCont {
  @Autowired
  private MpreturnDAO mpreturnDAO;
  
  @Autowired
  private MpproductDAO mpproductDAO;  
  
  public MpreturnCont(){
    System.out.println("MpreturnCont auto created...");
  }
  
  /**
   * 상품 그룹 등록
   * @return
   */
  @RequestMapping(value="/mpproduct/createReturn.do", method = RequestMethod.GET)
  public ModelAndView returncreateForm(MpreturnDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createReturn"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() 호출됨.... ");

    MpproductDTO mpproductDTO =  mpproductDAO.read(dto.getProductno());
    mav.addObject("mpproductDTO", mpproductDTO); 
    
    dto.setReturngroupno(mpproductDTO.getMpgroupno()); 
    dto.setReturnmcount(mpproductDTO.getMcount());    
    //mav.addObject("mpreturnDTO", dto);

    // product의 그룹번호 수량 가격 저장
    mav.addObject("mpreturnmpgroupno",dto.getReturngroupno());
    mav.addObject("mpreturnmpmcount",dto.getReturnmcount()); 
    
    // 출고 수량과 가격 String/int 오류 수정을 위한 값 저장
    mav.addObject("mpreturnretcount",dto.getRetcount());    
    
    return mav;
  }

  /**
   * 상품 그룹 실행
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/returncreate.do", method = RequestMethod.POST)  
  public ModelAndView returncreateProc(MpreturnDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request는 못쓰지만 request.setAttribute와 같음 //절대경로 가져오기(msgview에 사용)

    int totalcount = (int)dto.getReturnmcount() + (int)dto.getRetcount();
    MpproductDTO mpproductreturnDTO =  mpproductDAO.read(dto.getProductno());
    
    if(totalcount > 0){
      mpproductreturnDTO.setMcount(totalcount);
      mpproductDAO.update(mpproductreturnDTO);     
    }
    else{
      totalcount=0;
      mpproductreturnDTO.setMcount(totalcount);
      mpproductDAO.update(mpproductreturnDTO);
    }// else일때는 팝업창으로 에러 표시 하는것 고려!    
    
    
    int cnt = mpreturnDAO.create(dto);
    if(cnt == 1){
      mav.addObject("msg1", "상품 반품 등록을 했습니다.");
      mav.addObject("link1", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getReturngroupno()+"';\">");   
    }else{
      mav.addObject("msg1", "상품 반품 등록에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='목록' onclick=\"location.href='./list.do?mpgroupno="+dto.getReturngroupno()+"';\">");
    }
    return mav;
  }
  

  
}
