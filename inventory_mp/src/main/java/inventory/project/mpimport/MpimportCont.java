package inventory.project.mpimport;

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
public class MpimportCont {
  @Autowired
  private MpimportDAO mpimportDAO;
  
  @Autowired
  private MpproductDAO mpproductDAO;  
  
  public MpimportCont(){
    System.out.println("MpimportCont auto created...");
  }
  
  /**
   * ��ǰ �׷� ���
   * @return
   */
  @RequestMapping(value="/mpproduct/createImport.do", method = RequestMethod.GET)
  public ModelAndView importcreateForm(MpimportDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createImport"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() ȣ���.... ");

    MpproductDTO mpproductDTO =  mpproductDAO.read(dto.getProductno());
    mav.addObject("mpproductDTO", mpproductDTO); 
    
    dto.setImportgroupno(mpproductDTO.getMpgroupno()); 
    dto.setImportmcount(mpproductDTO.getMcount());
    //mav.addObject("mpproductDTO", dto);   
    
    
    // product�� �׷��ȣ ���� ���� ����
    mav.addObject("mpimportmpgroupno",dto.getImportgroupno());
    mav.addObject("mpimportmpmcount",dto.getImportmcount());
    mav.addObject("mpimportmpmprice",dto.getImportmprice());    
    
    // �԰� ������ ���� String/int ���� ������ ���� ����
    mav.addObject("mpimportimcount",dto.getImcount());
    mav.addObject("mpimportimprice",dto.getImprice());
    
    return mav;
  }

  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/createImport.do", method = RequestMethod.POST)  
  public ModelAndView importcreatecreateProc(MpimportDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request�� �������� request.setAttribute�� ���� //������ ��������(msgview�� ���)

    //System.out.println("totalcount::::::" +dto.getImportmcount() );
    
    int totalcount = (int)dto.getImportmcount() + (int)dto.getImcount();
    MpproductDTO mpproductimportDTO =  mpproductDAO.read(dto.getProductno());
    
    int cnt = mpimportDAO.create(dto);

    if(cnt == 1){

      if(totalcount >0){
        mpproductimportDTO.setMcount(totalcount);
        mpproductDAO.update(mpproductimportDTO);     
      }
      else{
        totalcount=0;
        mpproductimportDTO.setMcount(totalcount);
        mpproductDAO.update(mpproductimportDTO);
      }// else�϶��� �˾�â���� ���� ǥ�� �ϴ°� ���!    
   
      if(dto.getImprice() >0){
        mpproductimportDTO.setPrice(dto.getImprice());
        mpproductDAO.update(mpproductimportDTO);     
      }
      else{
        totalcount=0;
        mpproductimportDTO.setPrice(dto.getImportmprice());
        mpproductDAO.update(mpproductimportDTO);
      }// else�϶��� �˾�â���� ���� ǥ�� �ϴ°� ���!         
      
      
      mav.addObject("msg1", "��ǰ �԰��߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getImportgroupno()+"';\">");   
    }else{
      mav.addObject("msg1", "��ǰ �԰� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getImportgroupno()+"';\">"); 
    }
    
    return mav;
  }
  

  
}
