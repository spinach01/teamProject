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
 * @Controller : �����ڰ� ���������� URL�� �Է��Ͽ� ���ٽ� �ּҿ� �ش��ϴ� �żҵ带 �����ϰ� 
 *               �����͸� ó���Ͽ� JSP �������� �̵��ϴ� ����� ��   
 *               1. �ּҿ� �ش��ϴ� �޼ҵ� ����
 *               2. DTO, DAO���� ����Ÿ ó��
 *               3. JSP �������� �̵�
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
   * ��ǰ �׷� ���
   * @return
   */
  @RequestMapping(value="/mpproduct/createExport.do", method = RequestMethod.GET)
  public ModelAndView exoprtcreateForm(MpexportDTO dto){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/createExport"); // /mpgroup/createForm.jsp
    
    System.out.println(" createForm() ȣ���.... ");
 
    MpproductDTO mpproductDTO =  mpproductDAO.read(dto.getProductno());
    mav.addObject("mpproductDTO", mpproductDTO); 
    
    dto.setExportgroupno(mpproductDTO.getMpgroupno()); 
    dto.setExportmcount(mpproductDTO.getMcount());
    //mav.addObject("mpexportDTO", dto);
    
    // product�� �׷��ȣ ���� ���� ����
    mav.addObject("mpexportmpgroupno",dto.getExportgroupno());
    mav.addObject("mpexportmpmcount",dto.getExportmcount());
    mav.addObject("mpexportmpmprice",dto.getExportmprice());

    // ��� ������ ���� String/int ���� ������ ���� �� ����
    mav.addObject("mpexportexcount",dto.getExcount());
    mav.addObject("mpexportexprice",dto.getExprice());
    
    return mav;
  }

  /**
   * ��ǰ �׷� ����
   * @param dto
   * @return
   */
  @RequestMapping(value="/mpproduct/createExport.do", method = RequestMethod.POST)  
  public ModelAndView exportcreateProc(MpexportDTO dto, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mpproduct/msgView");  // /mediagroup/msgView.jsp
    mav.addObject("root", Utility.getRoot()); // /media //request�� �������� request.setAttribute�� ���� //������ ��������(msgview�� ���)
  
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
      }// else�϶��� �˾�â���� ���� ǥ�� �ϴ°� ���!             
      
      mav.addObject("msg1", "��ǰ ����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getExportgroupno()+"';\">");   
    }else{
      mav.addObject("msg1", "��ǰ ��� �����߽��ϴ�.");
      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick=\"history.back();\">");
      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do?mpgroupno="+dto.getExportgroupno()+"';\">"); 
    }
    
    return mav;
  }
 
}
