
  package com.com.books.bookify.Controllers;
  
  import org.springframework.stereotype.Controller;
import
  org.springframework.web.bind.annotation.RequestMapping;
  @Controller public class FileUploadController {
  @RequestMapping(value = "/home") public String homePage() { return "menu"; }
  }
 