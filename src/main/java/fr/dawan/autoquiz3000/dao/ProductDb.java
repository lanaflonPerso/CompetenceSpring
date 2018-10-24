package fr.dawan.autoquiz3000.dao;

public class ProductDb {
/*
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Product> findAll(){
		return (List<Product>)hibernateTemplate.find("FROM Product");
	}
	
	@Transactional(readOnly=true)
	public Product findById(int id) {
		return hibernateTemplate.get(Product.class, id);
	}
	
	@Transactional
	public void save(Product p) {
		hibernateTemplate.save(p);
	}
	
	@Transactional
	public void update(Product p) {
		hibernateTemplate.saveOrUpdate(p);
	}
	
	@Transactional(readOnly=true)
	public long nbProducts() {
		return (Long)hibernateTemplate.find("SELECT COUNT(c.id) FROM Product c").get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Product> findByDescription(String search){
		return (List<Product>)hibernateTemplate.find("FROM Product p WHERE p.description LIKE ?", search+"%");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Product> findByDescription2(String search){
		return hibernateTemplate.getSessionFactory().getCurrentSession()
		.createQuery("FROM Product p WHERE p.description LIKE :search")
		.setParameter(":search", search+"%")
		.list();
	}
	*/

	
	
	
	
}
