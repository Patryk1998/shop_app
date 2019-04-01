package project.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.library.dao.PieceDao;
import project.library.dao.TitleDao;
import project.library.dao.TypeDao;
import project.library.entities.dto.TitleDtoGet;
import project.library.entities.dto.TitleDtoPost;
import project.library.entities.library.Piece;
import project.library.entities.library.Title;
import project.library.entities.library.Type;
import project.library.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private Mapper mapper;

    //lista ksiazek w zaleznosci od noPieces(true->ksiazki z pieces=0 albo żadna dostepna też)
    public List<TitleDtoGet> getAllTitlesDto(boolean noPieces) {
        List<Title> titles = (List<Title>) titleDao.findAll();
        if(noPieces) {
            return titles.stream()
                    .map(title -> mapper.titleToTitleDto(title))
                    .collect(Collectors.toList());
        } else {
            return filterBooks(titles);
        }

    }

    public List<TitleDtoGet> filterBooks(List<Title> titles) {
        return titles.stream()
                    .filter(title -> title.getPieces().size()>0)
                    .filter(title -> title.getPieces().stream().anyMatch(piece -> piece.getRent()==null))
                    .map(title -> mapper.titleToTitleDto(title))
                    .collect(Collectors.toList());
    }

    public TitleDtoGet getTitleDtoById(Long id) {
        return mapper.titleToTitleDto(titleDao.findById(id).get());
    }

    public void changeBook(Long id, TitleDtoPost titleDto) {
        Title title = titleDao.findById(id).get();
        if(!title.getTitle().equals(titleDto.getTitle())) title.setTitle(titleDto.getTitle());
        if(!title.getAuthor().equals(titleDto.getAuthor())) title.setAuthor(titleDto.getAuthor());
        if(title.getSpendYear() != titleDto.getSpendYear()) title.setSpendYear(titleDto.getSpendYear());
        if(!title.getType().getName().equals(titleDto.getType())) {
            Type type = checkIfTypeExist(titleDto.getType());
            title.setType(type);
            type.setTitle(title);
        }
        titleDao.save(title);
    }

    public void deleteTitleById(Long id) {
        titleDao.delete(titleDao.findById(id).get());
    }

    public void addPieceToTitle(Long id) {
        Title title = titleDao.findById(id).get();
        Piece piece = new Piece(true);
        title.setPiece(piece);
        piece.setTitle(title);
        titleDao.save(title);
    }

    public void addTitle(TitleDtoPost titleDto) {
        Title title = mapper.titleDtoToTitle(titleDto);
        Type type = checkIfTypeExist(titleDto.getType());
        title.setType(type);
        type.setTitle(title);
        titleDao.save(title);
    }

    public List<Type> getAllTypes() {
        return (List<Type>) typeDao.findAll();
    }

    public Type checkIfTypeExist(String type) {
        return typeDao.findByName(type).orElse(new Type(type));
    }

}
