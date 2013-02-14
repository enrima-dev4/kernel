package at.ac.iiasa.ime.enrima.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
	@Value("#{prop['contact.base.url']}") private String contactBaseURL;
	@Value("#{prop['enrima.ver']}") private String ver;
	@Value("#{prop['app']}") private String app;
	@RequestMapping(value = "/contact",method=RequestMethod.GET)
	public String contact(@RequestParam("usr") String usr) {
		return "redirect:"+contactBaseURL+"?app="+app+"&ver="+ver+"&usr="+usr;
	}
}
