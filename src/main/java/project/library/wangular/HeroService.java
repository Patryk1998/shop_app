//package project.library.wangular;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class HeroService {
//    @Autowired
//    private HeroDao heroDao;
//
//    public void generateHeroes() {
//        for(int i = 0; i < 10; i ++) {
//            String name = "asd" + i;
//            Hero hero = new Hero(name);
//            heroDao.save(hero);
//        }
//    }
//
//    public Hero addHero(Hero hero) {
//        return heroDao.save(hero);
//    }
//
//    public List<Hero> getHeroes() {
//        generateHeroes();
//        return heroDao.findAll();
//    }
//
//    public Hero getHeroById(Integer id) {
//        return heroDao.findById(id).get();
//    }
//}
