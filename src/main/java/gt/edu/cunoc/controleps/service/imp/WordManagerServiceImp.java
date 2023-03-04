package gt.edu.cunoc.controleps.service.imp;

import com.spire.doc.Document;
import com.spire.doc.documents.BookmarksNavigator;
import gt.edu.cunoc.controleps.service.WordManagerService;

public class WordManagerServiceImp implements WordManagerService {

    @Override
    public void replaceBookMarks() {
        Document originalDoc = new Document("/home/angelrg/Documents/EPS_2023/TestReplace.docx");

        BookmarksNavigator bookmarksNavigator = new BookmarksNavigator(originalDoc);
        bookmarksNavigator.moveToBookmark("nombreEst");
        bookmarksNavigator.replaceBookmarkContent("Usuario de prueba", false);

        originalDoc.saveToFile("/home/angelrg/Documents/EPS_2023/TestReplace2.docx");
    }
}
