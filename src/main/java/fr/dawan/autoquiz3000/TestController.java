package fr.dawan.autoquiz3000;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.autoquiz3000.beans.Quiz;
import fr.dawan.autoquiz3000.beans.QuizQuestion;
import fr.dawan.autoquiz3000.beans.QuizResponse;
import fr.dawan.autoquiz3000.beans.Skill;
import fr.dawan.autoquiz3000.beans.StudentClass;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.beans.UserType;
import fr.dawan.autoquiz3000.ctrl.Ctrl;
import fr.dawan.autoquiz3000.dao.QuizDao;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private QuizDao qDao;

	@GetMapping("/user")
	public ModelAndView writeUser() {
		
		User vianney= new User();
		vianney.setBirthdate(new Date());
		vianney.setFirstName("vianney");
		vianney.setLastName("bailleux");
		vianney.setEmail("vianneyba@free.fr");
		vianney.setType(UserType.PROFESSOR);
		vianney.setPassword(Ctrl.MySQLPassword("aqwzsxedc"));
		
		User halford= new User();
		halford.setBirthdate(new Date());
		halford.setFirstName("rob");
		halford.setLastName("halford");
		halford.setEmail("halford@free.fr");
		halford.setType(UserType.STUDENT);
		halford.setPassword(Ctrl.MySQLPassword("aqwzsxedc"));
		
		User matos= new User();
		matos.setBirthdate(new Date());
		matos.setFirstName("andre");
		matos.setLastName("matos");
		matos.setEmail("matos@free.fr");
		matos.setType(UserType.STUDENT);
		matos.setPassword(Ctrl.MySQLPassword("aqwzsxedc"));
		
		User dickinson= new User();
		dickinson.setBirthdate(new Date());
		dickinson.setFirstName("andre");
		dickinson.setLastName("dickinson");
		dickinson.setEmail("dickinson@free.fr");
		dickinson.setType(UserType.STUDENT);
		dickinson.setPassword(Ctrl.MySQLPassword("aqwzsxedc"));
		
		List<User> listStudent= new ArrayList<>();
		listStudent.add(halford);
		listStudent.add(matos);
		listStudent.add(matos);
		
		StudentClass premierA= new StudentClass();
		premierA.setName(" classe de première");
		premierA.setStartDate(new Date());
		premierA.setEndDate(new Date());
		premierA.setStudents(listStudent);
		
		Quiz javaQuiz= new Quiz();
		javaQuiz.setName("QCM Java");
		javaQuiz.setScoreToAcquireSkill(60);
		javaQuiz.setStartDate(new Date());
		javaQuiz.setEndDate(new Date());
		
		Skill javaSkill= new Skill();
		javaSkill.setName("Java");

		javaQuiz.setSkill(javaSkill);
		
		// Question 1
		QuizQuestion qOne= new QuizQuestion();
		qOne.setOrderNum(1);
		qOne.setText("Quel package / structure est la plus récente pour les interfaces graphiques ?");
		
		QuizResponse rOneOne= new QuizResponse();
		rOneOne.setText("directx");
		rOneOne.setCorrect(false);
		QuizResponse rOneTwo= new QuizResponse();
		rOneTwo.setText("javafx");
		rOneTwo.setCorrect(true);
		QuizResponse rOneThree= new QuizResponse();
		rOneThree.setText("swing");
		rOneThree.setCorrect(false);
		QuizResponse rOneFour= new QuizResponse();
		rOneFour.setText("awt");
		rOneFour.setCorrect(false);
		
		List<QuizResponse> listROne= new ArrayList<>();
		listROne.add(rOneOne);
		listROne.add(rOneTwo);
		listROne.add(rOneThree);
		listROne.add(rOneFour);
		qOne.setQuizResponse(listROne);
		
		// Question 2
		QuizQuestion qTwo= new QuizQuestion();
		qTwo.setOrderNum(2);
		qTwo.setText("Le constructeur d'une classe doit porter le meme nom que la classe");
		
		QuizResponse rTwoOne= new QuizResponse();
		rTwoOne.setText("Pas obligatoirement");
		rTwoOne.setCorrect(false);
		QuizResponse rTwoTwo= new QuizResponse();
		rTwoTwo.setText("C'est déconseillé");
		rTwoTwo.setCorrect(false);
		QuizResponse rTwoThree= new QuizResponse();
		rTwoThree.setText("Oui, impérativement");
		rTwoThree.setCorrect(true);
		QuizResponse rTwoFour= new QuizResponse();
		rTwoFour.setText("Non, c'est interdit");
		rTwoFour.setCorrect(false);
		
		List<QuizResponse> listRTwo= new ArrayList<>();
		listRTwo.add(rTwoOne);
		listRTwo.add(rTwoTwo);
		listRTwo.add(rTwoThree);
		listRTwo.add(rTwoFour);
		qTwo.setQuizResponse(listRTwo);
		
		// Question 2
		QuizQuestion qThree= new QuizQuestion();
		qThree.setOrderNum(3);
		qThree.setText("J2ME, J2EE, J2SE sont adaptés respectivement pour quelles plateformes ?");
		
		QuizResponse rThreeOne= new QuizResponse();
		rThreeOne.setText("web, smartphone, desktop");
		rThreeOne.setCorrect(false);
		QuizResponse rThreeTwo= new QuizResponse();
		rThreeTwo.setText("web, desktop, smartphone");
		rThreeTwo.setCorrect(false);
		QuizResponse rThreeThree= new QuizResponse();
		rThreeThree.setText("smartphone, web, desktop");
		rThreeThree.setCorrect(true);
		QuizResponse rThreeFour= new QuizResponse();
		rThreeFour.setText("smartphone, desktop, web");
		rThreeFour.setCorrect(false);
		
		List<QuizResponse> listRThree= new ArrayList<>();
		listRThree.add(rThreeOne);
		listRThree.add(rThreeTwo);
		listRThree.add(rThreeThree);
		listRThree.add(rThreeFour);
		qThree.setQuizResponse(listRThree);
		
		List<QuizQuestion> listQJava= new ArrayList<>();
		listQJava.add(qOne);
		listQJava.add(qTwo);
		listQJava.add(qThree);
		
		javaQuiz.setQuizQuestions(listQJava);
		
		qDao.save(javaQuiz);
		
		return null;
		
	}
}
