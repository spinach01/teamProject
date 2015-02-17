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
 * @Controller : �����ڰ� ���������� URL�� �Է��Ͽ� ���ٽ� �ּҿ� �ش��ϴ� �żҵ带 �����ϰ� 
 *               �����͸� ó���Ͽ� JSP �������� �̵��ϴ� ����� ��   
 *               1. �ּҿ� �ش��ϴ� �޼ҵ� ����
 *               2. DTO, DAO���� ����Ÿ ó��
 *               3. JSP �������� �̵�
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
   * ��ǰ �׷� ���
   * @return
   */
  @RequestMapping(value="/mpgroup/create.do", method = RequestMethod.GET)
  public ModelAndView createForm(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/createForm"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() ȣ���.... ");
    
    return mav;
  }

  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/create.do", method = RequestMethod.POST)  
  public ModelAndView createProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request�� �������� request.setAttribute�� ���� //������ ��������(msgview�� ���)
  
    int cnt = mpgroupDAO.create(dto);
    if(cnt == 1){
      mav.addObject("msg1", "ǰ�� �׷��� ����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "ǰ�� �׷��� ��Ͽ� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }
  
  /**
   * ���� ȭ�� 
   * @return
   */
  @RequestMapping(value="/mpgroup/index.do", method = RequestMethod.GET)
  public ModelAndView index(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/index");  // /mpgroup/index.jsp
    mav.addObject("root", Utility.getRoot()); // request�� �������� request.setAttribute�� ����
  
    mav.addObject("index", mpgroupDAO.list());
    //= request.setAttribute("index", dao.list());//���� ���� �ǹ���...
  
    return mav;
  }   
  
  /**
   * ��ǰ �׷� ���
   * @return
   */
  @RequestMapping(value="/mpgroup/list.do", method = RequestMethod.GET)
  public ModelAndView list(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/list");  // /mpgroup/list.jsp
    mav.addObject("root", Utility.getRoot()); // request�� �������� request.setAttribute�� ����
  
    mav.addObject("list", mpgroupDAO.list());
    //= request.setAttribute("list", dao.list());//���� ���� �ǹ���...
  
    return mav;
  }
  
  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/update.do", method = RequestMethod.GET)
  public ModelAndView updateForm(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/updateForm"); // /mpgroup/updateForm.jsp
    
    System.out.println(" updateForm() ȣ���.... ");
    
    mav.addObject("dto", mpgroupDAO.read(dto.getMpgroupno()));
    // request.setAttribute("dto", dao.read(dto.getMpgroupno()));
    
    return mav;
  }  
  
  /**
   * ��ǰ �׷� ���� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/update.do", method = RequestMethod.POST)  
  public ModelAndView updateProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/msgView");      // /mpgroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // request�� �������� request.setAttribute�� ����
  
    int cnt = mpgroupDAO.update(dto);
    if(cnt == 1){
      mav.addObject("msg1", "ǰ�� �׷��� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "ǰ�� �׷��� ������ �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }
  
  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/delete.do", method = RequestMethod.GET)
  public ModelAndView deleteForm(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpgroup/deleteForm"); // /mpgroup/deleteForm.jsp
    
    System.out.println(" deleteForm() ȣ���.... ");
    
    mav.addObject("dto", dto);// ������ ��ȣ �ʿ��ϱ⿡~~
    // request.setAttribute("dto", dto);
    
    return mav;
  }  

  /**
   * ��ǰ �׷� ���� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpgroup/delete.do", method = RequestMethod.POST)  
  public ModelAndView deleteProc(MpgroupDTO dto){
    ModelAndView mav = new ModelAndView();   // Form�� input��ũ���� �ڵ����� ��
    mav.setViewName("/mpgroup/msgView");     // /mpgroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // request�� �������� request.setAttribute�� ����
  
    int cnt = mpgroupDAO.delete(dto.getMpgroupno());
    if(cnt == 1){
      mav.addObject("msg1", "ǰ�� �׷��� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">");    
    }else{
      mav.addObject("msg1", "ǰ�� �׷��� ������ �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='�׷� ���' onclick=\"location.href='list.do;'\">"); 
    }
    
    return mav;
  }  
  
}
