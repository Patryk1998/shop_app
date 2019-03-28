package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import project.library.dao.PieceDao;
import project.library.dao.RentDao;
import project.library.dao.TitleDao;
import project.library.dao.UserDao;
import project.library.entities.library.Piece;
import project.library.entities.library.Rent;
import project.library.entities.login.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class RentService {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PieceDao pieceDao;

    @Autowired
    private RentDao rentDao;

    public Long getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SecurityContextImpl impl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) impl.getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) token.getPrincipal();
        return userDetails.getId();
    }

    public void rentBook(Long titleId, Long userId) throws Exception {
        Piece piece = findAvailablePiece(titleId);
        if(piece == null) {
            throw new Exception("No piece available!");
        } else {
            User user = userDao.findById(userId).get();
            if(user.getRents() == null) {
                Rent rent = new Rent(piece, user);
                user.setRent(rent);
                userDao.save(user);
                pieceDao.save(piece);
            }
        }
    }

    public Piece findAvailablePiece(Long titleId) {
        Optional<Piece> piece = titleDao.findById(titleId).get().getPieces().stream()
                                    .filter(p -> p.getAvailability() == true)
                                    .findFirst();
        if(piece.isPresent()) {
            return piece.get();
        } else return null;
    }

    public void backBook(Long rentId, Long userId) {
        User user = userDao.findById(userId).get();
        user.getRents().remove(rentDao.findById(rentId));
        userDao.save(user);
    }
}
