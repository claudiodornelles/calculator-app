package com.claudiodornelles.cloudnative.servlet;

import com.claudiodornelles.cloudnative.annotation.AppConfig;
import com.claudiodornelles.cloudnative.commands.enums.OperationType;
import com.claudiodornelles.cloudnative.services.Calculator;
import com.claudiodornelles.cloudnative.interfaces.Operation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/app"})
public class CalculatorServlet extends HttpServlet {
    
    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    private final Calculator calculator = new Calculator(applicationContext);
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.println("<html><body>");
        writer.println("<h1>Calculator</h1>");
        try {
            if (request.getParameter("firstTerm") == null ||
                request.getParameter("secondTerm") == null ||
                request.getParameter("operation") == null) throw new IllegalArgumentException("Null parameters given");
            else {
                Double firstTerm = Double.parseDouble(request.getParameter("firstTerm"));
                Double secondTerm = Double.parseDouble(request.getParameter("secondTerm"));
                String requestedOperation = request.getParameter("operation").toUpperCase();
                OperationType operation;
                switch (requestedOperation) {
                    case "SUM":
                        operation = OperationType.SUM;
                        break;
                    case "SUBTRACT":
                        operation = OperationType.SUBTRACT;
                        break;
                    case "DIVIDE":
                        operation = OperationType.DIVIDE;
                        break;
                    case "MULTIPLY":
                        operation = OperationType.MULTIPLY;
                        break;
                    case "POWER":
                        operation = OperationType.POWER;
                        break;
                    default:
                        throw new IllegalArgumentException("A valid operation must be passed");
                }
                calculator.execute(operation, firstTerm, secondTerm);
                writer.println("<ul>");
                for (Operation executedOperation : calculator.getOperationsHistory()) {
                    writer.println("<li>" + executedOperation + "</li>");
                }
                writer.println("</ul>");
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            writer.println("<p>" + illegalArgumentException.getMessage() + "</p>");
            writer.println("<p> Please add to the end of URL a valid <strong><em>operation</strong></em>, a <strong><em>firstTerm</strong></em> and a <strong><em>secondTerm</strong></em>.</p>");
            writer.println("<p>Example:<br>Sum operation of 3 and 6<br><code>http://localhost:8090/calculator/app<strong><em>?operation=sum&firstTerm=3&secondTerm=6</em></strong></p>");
            writer.println("<ul>");
            writer.println("<li>Valid operations:</li>");
            writer.println("<ul>");
            writer.println("<li>SUM</li>");
            writer.println("<li>SUBTRACT</li>");
            writer.println("<li>DIVIDE</li>");
            writer.println("<li>MULTIPLY</li>");
            writer.println("<li>POWER</li>");
            writer.println("</ul>");
            writer.println("</ul>");
        } catch (Exception exception) {
            writer.println("<p>" + exception.getMessage() + "</p>");
        }
        writer.println("</body></html>");
    }
}