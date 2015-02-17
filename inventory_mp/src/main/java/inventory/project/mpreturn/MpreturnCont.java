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
 * @Controller : �����ڰ� ���������� URL�� �Է��Ͽ� ���ٽ� �ּҿ� �ش��ϴ� �żҵ带 �����ϰ� 
 *               �����͸� ó���Ͽ� JSP �������� �̵��ϴ� ����� ��   
 *               1. �ּҿ� �ش��ϴ� �޼ҵ� ����
 *               2. DTO, DAO���� ����Ÿ ó��
 *               3. JSP �������� �̵�
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
   * ��ǰ �׷� ���
   * @return
   */
  @RequestMapping(value="/mpproduct/createReturn.do", method = RequestMethod.GET)
  public ModelAndView returncreateForm(MpreturnDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createReturn"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() ȣ���.... ");

    MpproductDTO mpproductDTO =  mpproductDAO.read(dto.getProductno());
    mav.addObject("mpproductDTO", mpproductDTO); 
    
    dto.setReturngroupno(mpproductDTO.getMpgroupno()); 
    dto.setReturnmcount(mpproductDTO.getMcount());    
    //mav.addObject("mpreturnDTO", dto);

    // product�� �׷��ȣ ���� ���� ����
    mav.addObject("mpreturnmpgroupno",dto.getReturngroupno());
    mav.addObject("mpreturnmpmcount",dto.getReturnmcount()); 
    
    // ��� ������ ���� String/int ���� ������ ���� �� ����
    mav.addObject("mpreturnretcount",dto.getRetcount());    
    
    return mav;
  }

  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/returncreate.do", method = RequestMethod.POST)  
  public ModelAndView returncreateProc(MpreturnDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request�� �������� request.setAttribute�� ���� //������ ��������(msgview�� ���)

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
    }// else�϶��� �˾�â���� ���� ǥ�� �ϴ°� ���!    
    
    
    int cnt = mpreturnDAO.create(dto);
    if(cnt == 1){
      mav.addObject("msg1", "��ǰ ��ǰ ����� �߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getReturngroupno()+"';\">");   
    }else{
      mav.addObject("msg1", "��ǰ ��ǰ ��Ͽ� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getReturngroupno()+"';\">");
    }
    return mav;
  }
  

  
}
