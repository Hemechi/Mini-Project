package co;
import co.cstad.Features.*;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;

import java.util.Scanner;

public class Menu {
    public static void welcomeMsg() {
        System.out.println(
                "\n" +
                        " ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                        "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                        "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                        "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                        "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                        " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" +
                        "STOCK MANAGEMENT SYSTEM"
        );
    }

    public static void createTable() {
        Scanner sc = new Scanner(System.in);
        org.nocrala.tools.texttablefmt.Table table = new org.nocrala.tools.texttablefmt.Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        table.addCell("".repeat(4) + "| *)Display" + "".repeat(4));
        table.addCell("".repeat(4) + " | W)Write" + "".repeat(4));
        table.addCell("".repeat(4) + " | R)Read" + "".repeat(4));
        table.addCell("".repeat(4) + " | U)pdate" + "".repeat(4));
        table.addCell("".repeat(4) + " | D)elete" + "".repeat(4));
        table.addCell("".repeat(4) + " | F)irst" + "".repeat(4));
        table.addCell("".repeat(4) + " | P)revious" + "".repeat(4));
        table.addCell("".repeat(4) + " | N)ext" + "".repeat(4));
        table.addCell("".repeat(4) + " | L)ast" + "".repeat(4));
        table.addCell("".repeat(4) + "| S)earch" + "".repeat(4));
        table.addCell("".repeat(4) + " | G)oto" + "".repeat(4));
        table.addCell("".repeat(4) + " | Se)t row" + "".repeat(4));
        table.addCell("".repeat(4) + " | H)elp" + "".repeat(4));
        table.addCell("".repeat(4) + " | E)xit" + "".repeat(4));

        System.out.println(table.render());
        System.out.print("Command ->");
        String options = sc.nextLine();
        switch (options) {
            case "*" -> Display.displayFunction(Display.currentPage, Display.rowsPerPage);
            case "w", "W" -> Write.writeFunction();
            case "r", "R" -> Read.readFunction();
            case "u", "U" -> Update.updateFunction();
            case "d", "D" -> Delete.deleteFunction();
            case "f", "F" -> First.firstPageFunction(Display.currentPage, Display.rowsPerPage);
            case "p", "P" -> Previous.previousFunction(Display.currentPage, Display.rowsPerPage);
            case "n", "N" -> Next.nextFunction(Display.currentPage, Display.rowsPerPage);
            case "l", "L" -> Last.lastFunction(Display.currentPage, Display.rowsPerPage);
            case "s", "S" -> Search.searchFunction(Display.currentPage, Display.rowsPerPage);
            case "g", "G" -> Goto.gotoFunction(Display.currentPage, Display.rowsPerPage);
            case "se", "Se" -> SetRow.setRowFunction(Display.rowsPerPage);
            case "h", "H" -> Help.helpFunction();
            case "e", "E" -> {
                System.out.println("Have a Nice Day!! ");
                System.exit(0);
            }
            default -> System.out.println("Invalid Option");
        }
    }
}


