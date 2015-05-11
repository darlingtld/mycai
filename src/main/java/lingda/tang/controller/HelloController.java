package lingda.tang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public
    @ResponseBody
    String printWelcome() {
        return "hello";
    }
}