package inventory.project.group;

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
public class MpgroupCont {
  @Autowired
  private MpgroupDAO mpgroupDAO;
  
  @Autowired
  private MpproductDAO Mpproductdao;  
  
  public MpgroupCont(){
    System.out.println("MpgropCont auto created...");
  }
  
  /**
   * 상품 그룹 등록
   * @return
   */
  @RequestMapping(value="/mpgroup/create.do", method = RequestMethod.GET)
  public ModelAndView createForm(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/createForm"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() 호출됨.... ");
    
    return mav;
  }

  /**
   * 상품 그룹 실행
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/create.do", method = RequestMethod.POST)  
  public ModelAndView createProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request는 못쓰지만 request.setAttribute와 같음 //절대경로 가져오기(msgview에 사용)
  
    int cnt = mpgroupDAO.create(dto);
    if(cnt == 1){
      mav.addObject("msg1", "품목 그룹을 등록했습니다.");
      mav.addObject("link1", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "품목 그룹을 등록에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }
  
  /**
   * 시작 화면 
   * @return
   */
  @RequestMapping(value="/mpgroup/index.do", method = RequestMethod.GET)
  public ModelAndView index(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/index");  // /mpgroup/index.jsp
    mav.addObject("root", Utility.getRoot()); // request는 못쓰지만 request.setAttribute와 같음
  
    mav.addObject("index", mpgroupDAO.list());
    //= request.setAttribute("index", dao.list());//위와 같은 의미임...
  
    return mav;
  }   
  
  /**
   * 상품 그룹 목록
   * @return
   */
  @RequestMapping(value="/mpgroup/list.do", method = RequestMethod.GET)
  public ModelAndView list(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/list");  // /mpgroup/list.jsp
    mav.addObject("root", Utility.getRoot()); // request는 못쓰지만 request.setAttribute와 같음
  
    mav.addObject("list", mpgroupDAO.list());
    //= request.setAttribute("list", dao.list());//위와 같은 의미임...
  
    return mav;
  }
  
  /**
   * 상품 그룹 수정
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/update.do", method = RequestMethod.GET)
  public ModelAndView updateForm(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/updateForm"); // /mpgroup/updateForm.jsp
    
    System.out.println(" updateForm() 호출됨.... ");
    
    mav.addObject("dto", mpgroupDAO.read(dto.getMpgroupno()));
    // request.setAttribute("dto", dao.read(dto.getMpgroupno()));
    
    return mav;
  }  
  
  /**
   * 상품 그룹 수정 실행
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/update.do", method = RequestMethod.POST)  
  public ModelAndView updateProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/msgView");      // /mpgroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // request는 못쓰지만 request.setAttribute와 같음
  
    int cnt = mpgroupDAO.update(dto);
    if(cnt == 1){
      mav.addObject("msg1", "품목 그룹을 수정했습니다.");
      mav.addObject("link1", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "품목 그룹을 수정에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }
  
  /**
   * 상품 그룹 삭제
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/delete.do", method = RequestMethod.GET)
  public ModelAndView deleteForm(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/deleteForm"); // /mpgroup/deleteForm.jsp
    
    System.out.println(" deleteForm() 호출됨.... ");
    
    mav.addObject("dto", dto);// 삭제할 번호 필요하기에~~
    // request.setAttribute("dto", dto);
    
    return mav;
  }  

  /**
   * 상품 그룹 삭제 실행
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/delete.do", method = RequestMethod.POST)  
  public ModelAndView deleteProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();   // Form의 input태크들은 자동으로 들어감
    mav.setViewName("/mpgroup/msgView");     // /mpgroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // request는 못쓰지만 request.setAttribute와 같음
  
    int cnt = mpgroupDAO.delete(dto.getMpgroupno());
    if(cnt == 1){
      mav.addObject("msg1", "품목 그룹을 삭제했습니다.");
      mav.addObject("link1", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "품목 그룹을 삭제에 실패했습니다.");
      mav.addObject("link1", "<input type='button' value='다시 시도' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='그룹 목록' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }  
  
}
