package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.entity.Borrower;
import com.libararymangementsystem.demo.exceptionhandling.NotFoundException;
import com.libararymangementsystem.demo.repository.BarrowerRepository;
import com.libararymangementsystem.demo.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarrowerServiceImpl implements BarrowerService{
    @Autowired
    private BarrowerRepository barrowerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BorrowerDTO save(BorrowerDTO theBarrower) {
        /*Borrower b=new Borrower();
        b.setName(theBarrower.getName());
        b.setMobil(theBarrower.getMobile());
        b.setEmail(theBarrower.getEmail());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\"");
        Date date=sdf.parse(theBarrower.getBorrowingDate());
        b.setBorrowingDate(date);*/

        Borrower borrowers=modelMapper.map(theBarrower, Borrower.class);
        Borrower theBorrowers=barrowerRepository.findByEmail(borrowers.getEmail());
        if(theBorrowers!=null){
            throw new NotFoundException("This Email Is Already Taken By Another User " + theBarrower.getEmail());
        }
        barrowerRepository.save(borrowers);

       return modelMapper.map(borrowers,BorrowerDTO.class);
    }

    @Override
    public List<BorrowerDTO> findAll() {

        List<Borrower> barrowers=barrowerRepository.findAll();
        if(barrowers.isEmpty()){
            throw new NotFoundException("OOPS! No Result Found ");
        }

        List<BorrowerDTO> theBarrowersDTO=modelMapper.map(barrowers,new TypeToken<List<BorrowerDTO>>(){}.getType());
        return theBarrowersDTO;
    }

    @Override
    public BorrowerDTO findById(int barrowerId) {

        Optional<Borrower> result=barrowerRepository.findById(barrowerId);
        if(result.isEmpty()){
            throw new NotFoundException("Barrower Not Found Id-> " + barrowerId);
        }
        Borrower theBorrower=result.get();
        BorrowerDTO borrowerDTO=modelMapper.map(theBorrower, BorrowerDTO.class);
        return borrowerDTO;
    }

    @Override
    public void deleteById(int barrowerId) {

        Optional<Borrower> result=barrowerRepository.findById(barrowerId);
        if(result.isEmpty()){
            throw new NotFoundException("No Borrower Found With ID->" + barrowerId);
        }
        barrowerRepository.deleteById(barrowerId);

    }

    @Override
    public void deleteAll() {
        List<Borrower> borrowers=barrowerRepository.findAll();
        if(borrowers.isEmpty()){
            throw new NotFoundException("No Result Found!");
        }
        barrowerRepository.deleteAll();
    }

    @Override
    public BorrowerDTO saveUpdate(BorrowerDTO theBorrower) {
        Optional<Borrower> result=barrowerRepository.findById(theBorrower.getId());
        if(result.isEmpty()){
            throw new NotFoundException("Borrower Not Found Id-> " + theBorrower.getId());
        }
        Borrower borrower=modelMapper.map(theBorrower,Borrower.class);
        barrowerRepository.save(borrower);


        return modelMapper.map(borrower, BorrowerDTO.class);
    }

    @Override
    public void addBookToBorrower(int borrowerId, BookDTO theBook) {
        Optional<Borrower> borrower=barrowerRepository.findById(borrowerId);
        Borrower dbBorrower=null;
        if(borrower.isEmpty()){
            throw new NotFoundException("Borrower Not Found Id-> " + borrowerId);
        }else{
            dbBorrower=borrower.get();
            Book book=modelMapper.map(theBook,Book.class);
            dbBorrower.addBookByborrower(book);
        }

    }

    @Override
    public void addExBookToExBarrower(int borrowerId, Book theBook) {
        Optional<Borrower> borrower=barrowerRepository.findById(borrowerId);
        //Getting the book from database to put it in the hibernate context
        Optional<Book> book=bookRepository.findById(theBook.getId());
         Borrower dbBarrower=null;
         Book dbBook=null;
         if(borrower.isEmpty()){
             throw new NotFoundException("Borrower Not Found Id-> " + borrowerId);
         } else if (book.isEmpty()) {
             throw new NotFoundException("Book Not Found Id-> " + theBook.getId());
         }
         else{
             dbBarrower=borrower.get();
             dbBook=book.get();
             dbBarrower.getBooks().add(dbBook);
         }


    }

}
