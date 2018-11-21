package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.library.dao.PieceDao;
import project.library.dao.TitleDao;
import project.library.entities.Piece;
import project.library.entities.PieceStatus;
import project.library.entities.Title;
import project.library.enums.PieceStatusEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookService {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private PieceDao pieceDao;

    public Title createExample() {

        PieceStatus pieceStatus = new PieceStatus();
        pieceStatus.setStatus(PieceStatusEnum.DAMAGED_AVAILABLE.getStatus());
        pieceStatus.setAvailability(PieceStatusEnum.DAMAGED_AVAILABLE.getAvailability());

        Title title = new Title();
        title.setAuthor("author1");
        title.setTitle("title");

        HashSet<PieceStatus> statuses = new HashSet<>();
        statuses.add(pieceStatus);
        Piece piece = new Piece();
        piece.setStatus(statuses);
        piece.setTitle(title);

        List<Piece> pieces = new ArrayList<>();
        pieces.add(piece);
        pieceStatus.setPieces(pieces);
        title.setPieces(pieces);

        return title;
    }

    public void saveTitle() {
        titleDao.save(createExample());
    }
}
