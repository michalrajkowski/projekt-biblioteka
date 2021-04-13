package main.java.service;

import main.java.model.Book;
import main.java.model.Opinion;
import main.java.model.User;
import main.java.repository.OpinionRepository;

import java.sql.SQLException;
import java.util.List;

public class OpinionService {

    private OpinionRepository opinionRepository;

    public OpinionService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public void createOpinion(int rate, String opinionText, User user, Book book) throws SQLException {


        List<Opinion> opinie = opinionRepository.findByBookId(book.id);

        Opinion opinion = findOpinionByUserID(opinie, user.id);

        if(opinion != null){
            return;
        }



        Opinion newOpinion = new Opinion(
                -1,
                rate,
                opinionText,
                user.id,
                book.id
        );



        opinionRepository.save(newOpinion);

    }

    public void deleteOpinion(User user, Book book) throws SQLException {

        List<Opinion> opinie = opinionRepository.findByBookId(book.id);

        Opinion opinion = findOpinionByUserID(opinie, user.id);

        if(opinion != null){
            opinionRepository.remove(opinion);
        }
    }

    public List<Opinion> browseOpinions(Book book) throws SQLException {
        List<Opinion> opinie = opinionRepository.findByBookId(book.id);
        return opinie;
    }

    public void editOpinion(String editedOpinionText, User user, Book book) throws SQLException {


        List<Opinion> opinie = opinionRepository.findByBookId(book.id);

        Opinion opinion = findOpinionByUserID(opinie, user.id);

        if(opinion != null){
            opinion.text = editedOpinionText;
            deleteOpinion(user, book);
            opinionRepository.save(opinion);
        }
    }

    public void createOpinionPlus(int rate, String opinionText, User user, Book book) throws SQLException {


        List<Opinion> opinie = opinionRepository.findByBookId(book.id);

        Opinion opinion = findOpinionByUserID(opinie, user.id);

        if(opinion != null){
            deleteOpinion(user, book);
        }

            Opinion newOpinion = new Opinion(
                    -1,
                    rate,
                    opinionText,
                    user.id,
                    book.id
            );



            opinionRepository.save(newOpinion);
    }

    public Opinion findOpinionByUserID(List<Opinion> opinie, int _userId){
        for(int i = 0; i < opinie.size(); i++){
            if(opinie.get(i).userId == _userId)
                return opinie.get(i);
        }
        return null;
    }
}
