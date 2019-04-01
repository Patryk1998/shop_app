package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import project.library.dao.PieceDao;
import project.library.dao.RentDao;
import project.library.dao.TitleDao;
import project.library.dao.UserDao;
import project.library.entities.dto.RentDto;
import project.library.entities.library.Piece;
import project.library.entities.library.Rent;
import project.library.entities.login.User;
import project.library.mapper.Mapper;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private Mapper mapper;

    public Long getUserIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SecurityContextImpl impl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) impl.getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) token.getPrincipal();
        return userDetails.getId();
    }

    public List<RentDto> getUserRentsDto(Long userId) {
        return userDao.findById(userId).get().getRents().stream()
                    .map(rent -> mapper.rentToRentDto(rent))
                    .collect(Collectors.toList());
    }

    public void rentBook(Long titleId, Long userId) throws Exception {
        Piece piece = findAvailablePiece(titleId);
        if(piece == null) {
            throw new Exception("No piece available!");
        } else {
            User user = userDao.findById(userId).get();
            Rent rent = new Rent(piece, user);
            user.setRent(rent);
            userDao.save(user);
            pieceDao.save(piece);

        }
    }

    public Piece findAvailablePiece(Long titleId) {
        Optional<Piece> piece = titleDao.findById(titleId).get().getPieces().stream()
                                    .filter(p -> p.getRent()==null)
                                    .findFirst();
        if(piece.isPresent()) {
            return piece.get();
        } else return null;
    }

    public Long backBook(Long rentId, Long userId) {
        User user = userDao.findById(userId).get();
        Rent rent = rentDao.findById(rentId).get();
        Piece piece = pieceDao.findById(rent.getPiece().getId()).get();
        user.getRents().remove(rent);
        piece.setRent(null);
        rentDao.delete(rent);
        User user1 = userDao.save(user);
        Piece piece1 = pieceDao.save(piece);
        //temporaty, dlaczego id po ponownym zapisie w user1 sie zmienia???
        return user1.getId();
    }
}
