package fr.dawan.autoquiz3000;
import fr.dawan.autoquiz3000.beans.User;
import fr.dawan.autoquiz3000.ctrl.CtrlUser;
import fr.dawan.autoquiz3000.dao.UserDao;

public class test {

	public static void main(String[] args) {
		User user= new User();
		CtrlUser ctrl = new CtrlUser("nono", "patrick", "vianneyba@free.fr", "1979-05-03");
		if(!ctrl.isError()) {
			UserDao dao= new UserDao();
			System.out.println(ctrl.getUser());
			dao.save(ctrl.getUser());
		}
	}
}
