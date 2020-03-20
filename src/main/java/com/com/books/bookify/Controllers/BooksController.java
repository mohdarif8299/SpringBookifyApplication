package com.com.books.bookify.Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.com.books.bookify.dao.UserDao;
import com.com.books.bookify.exceptions.InvalidUserPassword;
import com.com.books.bookify.exceptions.UserNoFoundException;
import com.com.books.bookify.models.AvailableBooks;
import com.com.books.bookify.models.POJO;
import com.com.books.bookify.models.UserDetails;
import com.com.books.bookify.repository.BookiRepository;
import com.com.books.bookify.repository.InsertBookRepository;
import com.com.books.bookify.repository.UploadBookRepository;
import com.com.books.bookify.sell.BookUploadModel;
import com.com.books.bookify.storage.FileStorageService;

@RestController
public class BooksController {

	@Autowired
	BookiRepository repository;
	@Autowired
	private ServletContext servletContext;
	public static final String uploadingDir = System.getProperty("user.dir") + "/src/main/resources/";
	public static final String uploadingDirBooks = System.getProperty("user.dir") + "/src/main/resources/books_images/";
	@Autowired
	FileStorageService storageService;
	static String username_id;
	@Autowired
	UploadBookRepository bookRepository;
	@Autowired
	InsertBookRepository insertRepository;
	@Autowired
	UserDao userDao;


	@PostMapping("/login")
	@ResponseBody
	public UserDetails func(@RequestBody String response) {
		JSONObject jsonObject = new JSONObject(response);
		String uid = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		if (!userDao.checkUsername(uid)) {
			throw new UserNoFoundException("Invalid Username");
		} else {
			UserDetails userDetails = repository.findById(uid).orElseThrow(() -> new UserNoFoundException(uid));
			if (Objects.equals(password, userDetails.getPassword())) {
				return userDetails;
			} else {
				throw new InvalidUserPassword("Invalid Password");
			}
		}
	}

	@PostMapping("/register")
	@ResponseBody
	private UserDetails createNewUser(@RequestBody String response) {
		JSONObject jsonObject = new JSONObject(response);
		if (userDao.checkUsername(jsonObject.getString("username"))) {
			System.out.println("user with this username already exixts");
			return null;
		} else {
			UserDetails userDetails = new UserDetails(jsonObject.getString("username"), jsonObject.getString("name"),
					jsonObject.getString("password"), null, null);
			return repository.save(userDetails);
		}
	}

	@PutMapping("/updateUser/{uid}")
	@ResponseBody
	private UserDetails updateuser(@PathVariable String uid, @RequestBody String response) {
		System.out.println(response);
		JSONObject jsonObject = new JSONObject(response);
		UserDetails userDetails = new UserDetails(jsonObject.getString("username"), jsonObject.getString("name"),
				jsonObject.getString("password"), jsonObject.getString("number"), null);

		return repository.findById(uid).map(user -> {
			user.setName(userDetails.getName());
			user.setNumber(userDetails.getNumber());
			return repository.save(user);
		}).orElseGet(() -> {
			userDetails.setUsername(uid);
			return repository.save(userDetails);
		});
	}

	@PostMapping("/uploadbook")
	@ResponseBody
	private BookUploadModel uploadBook(@RequestParam("images") MultipartFile[] files,
			@RequestParam("username") String username, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("date") String date,
			@RequestParam("price") String price, @RequestParam("category") String category,
			@RequestParam("isbn") Long id) {
		String uniqueID = UUID.randomUUID().toString();
		ArrayList<String> list = new ArrayList<>();
		for (MultipartFile file : files) {
			File f = new File(uploadingDirBooks + file.getOriginalFilename());
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(f.getPath());
		}
		BookUploadModel b = new BookUploadModel(uniqueID, id, list.get(0), list.get(1), list.get(2), list.get(3), title,
				description, category, price, username, date);
		return bookRepository.save(b);
	}
@GetMapping("/") 
@ResponseBody 
public String helloWorld() {
	return "SpringBOot On Server";
}
	@GetMapping("/alluploadedbooks")
	@ResponseBody
	private List<BookUploadModel> allBooksUploaded() {
		return bookRepository.findAll();
	}

	@GetMapping("/insertbook")
	@ResponseBody
	private AvailableBooks insertNewBook() {
		AvailableBooks books = new AvailableBooks("corejava1", "Cay S. Horstmana", "977", "9788131725351",
				"Core Java Volume II. Advance Features", "Designed for serious programmers, this reliable, unbiased, no-nonsense tutorial illuminates advanced Java language and library features with thoroughly tested code examples. As in previous editions, all code is easy to understand and displays modern best-practice solutions to the realworld challenges faced by professional developers..", 
				"https://firebasestorage.googleapis.com/v0/b/meats-97b97.appspot.com/o/corejava.png?alt=media&token=bdbd6b04-3785-4414-a10e-3977c3256afe",
				"8th Edition", "1", "Computer Science and Programming", "350");
		return insertRepository.save(books);
	}
	@PostMapping("/upload_profile_pic1")
	@ResponseBody
	public Optional<UserDetails> uploadFile(@RequestParam("image") MultipartFile file,
			@RequestParam("username") String username, HttpServletResponse response) {
		File f = new File(uploadingDir + file.getOriginalFilename());
		Optional<UserDetails> users = null;
		try {
			file.transferTo(f);
			System.out.println(username);
			username_id = username;
			users = this.getUserDetails(username, f);

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.sendRedirect("/sid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@RequestMapping(value = "/sid", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage() throws IOException, InterruptedException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		ClassPathResource imgFile = new ClassPathResource(username_id + ".png");
		InputStream is = imgFile.getInputStream();
		byte[] bytes = StreamUtils.copyToByteArray(is);
		is.close();
		return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	}

	public Optional<UserDetails> getUserDetails(String username, File f) {
		return repository.findById(username).map(user -> {
			user.setImage(f.getPath());
			System.out.println(user.getImage());
			return repository.save(user);
		});
	}

	public Optional<AvailableBooks> getBookDetails(String isbn, File f) {
		return insertRepository.findById(isbn).map(book -> {
			book.setImage(f.getPath());
			System.out.println(book.getImage());
			return insertRepository.save(book);
		});
	}

	@GetMapping("/getallbooks")
	@ResponseBody
	private List<AvailableBooks> getBook() {
		return insertRepository.findAll();
	}
	@PostMapping("/upload_profile_pic")
	@ResponseBody
	public ResponseEntity<Object> uploadImages(@RequestParam("image") MultipartFile file,
			@RequestParam("username") String username, HttpServletResponse response) {
		File f = new File(uploadingDir + file.getOriginalFilename());
		try {
			Optional<UserDetails> users = this.getUserDetails(username, f);
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(file.getBytes());
			fos.flush();
			username_id = username;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.sendRedirect("/sid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Uploaded Successfully", HttpStatus.OK);
	}
}
