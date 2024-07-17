package com.libararymangementsystem.demo.restcontroller;


import com.libararymangementsystem.demo.dtos.BookDTO;
import com.libararymangementsystem.demo.dtos.BorrowerDTO;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.entity.Borrower;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponse;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponseWithEntity;
import com.libararymangementsystem.demo.service.BarrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BarrowerController {

   // final static Logger logger = Logger.getLogger(BarrowerController.class);
    @Autowired
    private BarrowerService barrowerService;


    //SAVING A BORROWER AND RETURN  ONLY AN OBJECT
    @PostMapping("/barrowers")
    public ResponseEntity<BorrowerDTO> addBarrower(@RequestBody BorrowerDTO theBarrower){
        /*try {
            impl.save(theBarrower);
            logger.info("Sucess adding Barrower :email"+theBarrower.getEmail());
            return new ResponseEntity<>(new ErrorResponse(200,"Sucess",new Date().getTime()), HttpStatus.OK);
            // Borrower dbBorrower=barrowerService.save(theBarrower);
        }
        catch(Exception exc)
        {
            logger.error("Error while adding Barrower",exc);
            return new ResponseEntity<>(new ErrorResponse(500,exc.getMessage(),new Date().getTime()), HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        //FOR AUTOMATIC SAVING OF CURRENT DATE AND TIME
        theBarrower.setBorrowingDate(LocalDateTime.now());
        theBarrower.setId(0);
       BorrowerDTO baro= barrowerService.save(theBarrower);

       return  ResponseEntity.ok(baro);

    }

    //SAVING A BORROWER AND RETURN ONLY A SUCCESS MESSAGE IN RESPONSE ENTITY
    @PostMapping("/borrowers")
    public ResponseEntity<SuccessResponse> addBorrower(@RequestBody BorrowerDTO theBarrower){
        //SETTING THE BORROWING DATE TO THE CURRENT LOCAL DATE TIME
        //WITH THIS APPROACH NO NEED TO SEND BORROWING DATE IN JSON
        //IT ADDED AUTOMATICALLY TO THE DATABASE COLUMN
        theBarrower.setBorrowingDate(LocalDateTime.now());

        //in case a user enters an id in JSON
        theBarrower.setId(0);

        //SAVING THE BORROWER
        barrowerService.save(theBarrower);
        //THE SUCCESS MESSAGE
        String successMessage="Borrower Saved Successfully";
        //CREATING AN OBJECT OF SUCCESS RESPONSE
        SuccessResponse response= new SuccessResponse();
        //SETTING THE SUCCESS MESSAGE TO THE PROVIDED MESSAGE
        response.setMessage(successMessage);

        //RETURNING A SUCCESS MESSAGE WITH OK HTTP STATUS CODE
        return ResponseEntity.ok(response) ;
    }
    //SAVING A BORROWER AND RETURN A SUCCESS MESSAGE&&BORROWER OBJECT IN THE RESPONSE ENTITY
    @PostMapping("/borrowerss")
    public ResponseEntity<SuccessResponseWithEntity<BorrowerDTO>> addBorrowerr(@RequestBody BorrowerDTO theBorrower){

        theBorrower.setBorrowingDate(LocalDateTime.now());
        //in case a user enters an id in JSON
        theBorrower.setId(0);

        BorrowerDTO borr=barrowerService.save(theBorrower);
        String successMessage="SUCCESS SAVED Email-> " + theBorrower.getEmail();
        SuccessResponseWithEntity<BorrowerDTO> response=new SuccessResponseWithEntity<>();
        response.setMessage(successMessage);
        response.setEntity(borr);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/barrowers")
    public ResponseEntity<SuccessResponseWithEntity<List<BorrowerDTO>> >findAll(){
           List<BorrowerDTO> borrowers=barrowerService.findAll();
           String successMessage="The Following Result Is Founded";
           SuccessResponseWithEntity<List<BorrowerDTO>> response=new SuccessResponseWithEntity<>();
           response.setMessage(successMessage);
           response.setEntity(borrowers);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/barrowers/{barrowerId}")
    public ResponseEntity<SuccessResponseWithEntity<BorrowerDTO>>findById(@PathVariable int barrowerId){
        BorrowerDTO baro=barrowerService.findById(barrowerId);
        String successMessage="Borrower Founded ID-> " + barrowerId;
        SuccessResponseWithEntity<BorrowerDTO> response=new SuccessResponseWithEntity<>();
        response.setMessage(successMessage);
        response.setEntity(baro);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/barrowers/{barrowerId}")
    public ResponseEntity<SuccessResponse> deleteById(@PathVariable int barrowerId){

        barrowerService.deleteById(barrowerId);
        String successMessage="Borrower Deleted Successfully ID-> " + barrowerId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/barrowers")
    public ResponseEntity<SuccessResponse> deleteAll(){
        barrowerService.deleteAll();
        String successMessage="Borrowers Deleted Successfully!";
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/borrowers")
    public ResponseEntity<SuccessResponseWithEntity<BorrowerDTO>> updateBorrower(@RequestBody BorrowerDTO theBorrower){
            theBorrower.setBorrowingDate(LocalDateTime.now());

            BorrowerDTO bar = barrowerService.saveUpdate(theBorrower);
            String successMessage = "Borrower Updated Successfully Id-> " + theBorrower.getId();
            SuccessResponseWithEntity<BorrowerDTO> response = new SuccessResponseWithEntity<>();
            response.setMessage(successMessage);
            response.setEntity(bar);
            return ResponseEntity.ok(response);


    }
    //ADD NEW CREATED BOOK TO EXISTING BORROWER
    @PostMapping("/borrowers/{borrowerId}")
    public ResponseEntity<SuccessResponse> addBookToBorrower(@PathVariable int borrowerId,
                                                             @RequestBody BookDTO theBook){
        barrowerService.addBookToBorrower(borrowerId,theBook);
        String successMessage="Book Added Successfully To Borrower id-> " + borrowerId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);

        return ResponseEntity.ok(response);

    }

    //ADD EXISTING BOOK TO EXISTING BORROWER
    @PostMapping("/exborrowers/{borrowerId}")
    public ResponseEntity<SuccessResponse> addExBookToExBarrower(@PathVariable int borrowerId,
                                                                 @RequestBody Book theBook){
        barrowerService.addExBookToExBarrower(borrowerId,theBook);
        String successMessage="Book Added Successfully To Borrower id-> " + borrowerId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(successMessage);

        return ResponseEntity.ok(response);
    }
}
