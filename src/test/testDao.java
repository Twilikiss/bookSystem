package test;

import com.bean.Book;
import com.bean.Manager;
import com.bean.Page;
import com.server.BookServer;
import com.server.ManagerServer;
import org.junit.Test;

import java.util.List;

public class testDao {
    public static void main(String[] args) throws Exception {
        List<Manager> gdou123456 = ManagerServer.Managerlogin("gdou123456", "123456789");

        System.out.println(gdou123456.size());

        Page<Book> bookPage = BookServer.queryForItem(0, 10);

       System.out.println(bookPage);
    }

    @Test
    public void testadd(){
        Book book = new Book(null,"dwd","dwdw", 12.0, 12, 12);
        BookServer.addBook(book);
    }

    @Test
    public void testdelete(){

        BookServer.deleteBook(45);
    }

    @Test
    public void testupdate(){

        Book book = new Book(null,"dwd","dwdw", 12.0, 12, 12);
        BookServer.updateBook(book, 46);
    }
}
