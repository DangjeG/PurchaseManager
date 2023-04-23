package WebApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/")
    public String getPage(){
        return "main";
    }
    @RequestMapping("/create")
    public String getCreatePage(){
        return "create";
    }
}
