import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GenerationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String textToEncode = request.getParameter("textToEncode");
        GenerateQRCode.generateQRimage(textToEncode);
        response.setContentType("image/jpg");

    }
}
