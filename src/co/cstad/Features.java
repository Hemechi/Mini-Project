package co.cstad;

import co.cstad.StockManagement;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.time.LocalDate;
import java.util.*;
public class Features {
    public class Write {

        static List<StockManagement> stockManagementArrayList = new ArrayList<>();
        static StockManagement stockManagement = new StockManagement();
        static Scanner scanner = new Scanner(System.in);
        static Random random = new Random();

        public static void writeFunction() {
            int randomNumber = random.nextInt(20) + 1;
            stockManagement.setDate(LocalDate.now());
            Integer proID = stockManagement.setId(randomNumber);
            System.out.println("Product ID : " + proID);
            System.out.print("Product's name : ");
            String proName = stockManagement.setName(scanner.next());
            System.out.print("Product's Price : ");
            Integer proPrice = stockManagement.setUnitPrice(scanner.nextInt());
            System.out.print("Product's Qty : ");
            Integer proQty = stockManagement.setQty(scanner.nextInt());
            addItem(new StockManagement(proID, proName, proPrice, proQty, LocalDate.now()));
            Table table = getTable();
            System.out.println(table.render());
            System.out.print("Are you sure want to add this record? [Y/y] or [N/n]: ");
            String userInput = scanner.next();
            if (userInput.equals("Y") || Objects.equals(userInput, "y")) {
                System.out.println("~".repeat(30));
                System.out.println(stockManagement.getId() + " was added sucessfully ");
                System.out.println("~".repeat(30));
            } else if (userInput.equals("N") || Objects.equals(userInput, "n")) {
                removeItem(stockManagement);
                System.out.println("Product is not added");
            }
        }

        private static Table getTable() {
            Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
            table.addCell("ID" + " ".repeat(20) + ": " + stockManagement.getId());
            table.addCell("Name" + " ".repeat(18) + ": " + stockManagement.getName());
            table.addCell("Unit Price" + " ".repeat(12) + ": " + stockManagement.getUnitPrice());
            table.addCell("Qty" + " ".repeat(19) + ": " + stockManagement.getQty());
            table.addCell("Imported Date" + " ".repeat(9) + ": " + stockManagement.getDate());
            return table;
        }
        public static void addItem(StockManagement stockManagement) {
            stockManagementArrayList.add(stockManagement);
        }

        public static void removeItem(StockManagement stockManagement) {
            stockManagementArrayList.remove(stockManagement);
        }
    }
    public class Delete {
        public static void deleteFunction() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the ID of the product you want to delete: ");
            Integer productId = Integer.parseInt(sc.nextLine());
            for (StockManagement product : Write.stockManagementArrayList) {
                if (product.getId().equals(productId)) {
                    Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                    table.addCell(" ID            : " + product.getId() + " ".repeat(10));
                    table.addCell(" Name          : " + product.getName() + " ".repeat(10));
                    table.addCell(" Unit price    : " + product.getUnitPrice() + " ".repeat(10));
                    table.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                    table.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                    System.out.println(table.render());
                    System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                    String options = sc.nextLine();
                    switch (options) {
                        case "y", "Y" -> {
                            Write.stockManagementArrayList.remove(product);
                            System.out.println("Product with ID " + productId + " deleted successfully.");
                            return;
                        }
                        case "n", "N" -> {
                            System.out.println("Product is not deleted");
                            return;
                        }
                        default -> System.out.println("Invalid options");
                    }
                }
            }
        }
    }
    public class First {
        public static int firstPageFunction(int currentPage, int rowsPerPage) {
            if (currentPage == 1) {
                System.out.println("You are already on the first page.");
            } else {
                currentPage = 1;
                Display.displayFunction(currentPage, rowsPerPage);
            }
            return  currentPage;
        }
    }
    public class Goto {
        public static int gotoFunction(int currentPage, int rowsPerPage) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the page number you want to go to: ");

            int targetPage = scanner.nextInt();
            int totalPages = (int) Math.ceil((double) Write.stockManagementArrayList.size() / rowsPerPage);

            if (targetPage >= 1 && targetPage <= totalPages) {
                currentPage = targetPage;
                Display.displayFunction( currentPage, rowsPerPage);
            } else {
                System.out.println("Invalid page number. Please enter a page number between 1 and " + totalPages + ".");
            }
            return currentPage;
        }
    }
    public class Last {
        public static Integer lastFunction(int currentPage, int rowsPerPage) {
            int totalPages = (int) Math.ceil((double) Write.stockManagementArrayList.size() / rowsPerPage);
            if (currentPage == totalPages) {
                System.out.println("You are already on the last page.");
            } else {
                currentPage = totalPages;
                Display.displayFunction(currentPage, rowsPerPage);
            }
            return currentPage;
        }
    }
    public class Search {
        public static void searchFunction(int currentPage, int rowsPerPage) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Search product by keyword: ");
            String searchKeyword = scanner.nextLine().toLowerCase();

            List<StockManagement> matchingProducts = new ArrayList<>();

            for (StockManagement product : Write.stockManagementArrayList) {
                String productName = product.getName().toLowerCase();

                if (productName.contains(searchKeyword)) {
                    matchingProducts.add(product);
                }
            }

            int totalPages = (int) Math.ceil((double) matchingProducts.size() / rowsPerPage);
            if (matchingProducts.isEmpty()) {
                System.out.println("No products found containing the keyword '" + searchKeyword + "'.");
            } else {
                if (currentPage < 1) {
                    currentPage = 1;
                } else if (currentPage > totalPages) {
                    currentPage = totalPages;
                }

                int startIndex = (currentPage - 1) * rowsPerPage;
                int endIndex = Math.min(startIndex + rowsPerPage, matchingProducts.size());

                Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                tableDisplay.addCell(" ".repeat(2) + "ID" + " ".repeat(2));
                tableDisplay.addCell(" ".repeat(2) + "Name" + " ".repeat(2));
                tableDisplay.addCell(" ".repeat(2) + "Unit Price" + " ".repeat(2));
                tableDisplay.addCell(" ".repeat(2) + "Qty" + " ".repeat(2));
                tableDisplay.addCell(" ".repeat(2) + "Imported Date" + " ".repeat(2));

                for (int i = startIndex; i < endIndex; i++) {
                    StockManagement product = matchingProducts.get(i);
                    tableDisplay.addCell(" ".repeat(2) + product.getId().toString());
                    tableDisplay.addCell(" ".repeat(2) + product.getName());
                    tableDisplay.addCell(" ".repeat(2) + product.getUnitPrice().toString());
                    tableDisplay.addCell(" ".repeat(2) + product.getQty().toString());
                    tableDisplay.addCell(" ".repeat(2) + product.getDate().toString());
                }

                System.out.println(tableDisplay.render());
                System.out.println("~ ".repeat(60));

                System.out.println("Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total matching products: " + matchingProducts.size());
                System.out.println("~ ".repeat(60));
            }
        }
    }
    public class SetRow {
        public static int setRowFunction( int rowsPerPage) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("Enter number of row(s) you want to display : ");
                int numberOfRows = Integer.parseInt(sc.nextLine());
                if (numberOfRows >0 && numberOfRows <= Write.stockManagementArrayList.size()){
                    return numberOfRows;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return rowsPerPage;
        }
    }
    public class Update {
        public static void updateFunction() {
            Scanner sc = new Scanner(System.in);
            boolean isFound = false;
            try {
                System.out.print("Enter ID to update : ");
                Integer idToUpdate = Integer.parseInt(sc.nextLine());
                for (StockManagement stockManagement : Write.stockManagementArrayList) {
                    if (stockManagement.getId().equals(idToUpdate)) {
                        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                        table.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                        table.addCell(" Name          : " + stockManagement.getName() + " ".repeat(10));
                        table.addCell(" Unit price    : " + stockManagement.getUnitPrice() + " ".repeat(10));
                        table.addCell(" Qty           : " + stockManagement.getQty() + " ".repeat(10));
                        table.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                        System.out.println(table.render());
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                    System.out.println("Product with ID : " + idToUpdate + " is not found");
                }
                StockManagement productToUpdate = null;

                for (StockManagement product : Write.stockManagementArrayList) {
                    if (product.getId().equals(idToUpdate)) {
                        productToUpdate = product;
                        isFound = true;
                        break;
                    }
                }

                if (!isFound) {
                    System.out.println("Product with ID: " + idToUpdate + " is not found");
                    return;
                }
                StockManagement product = productToUpdate;

                System.out.println("What do you want to update?");
                Table tableToUpdate = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND);
                tableToUpdate.addCell(" ".repeat(2) + "1. All" + " ".repeat(2));
                tableToUpdate.addCell(" ".repeat(2) + "2. Name" + " ".repeat(2));
                tableToUpdate.addCell(" ".repeat(2) + "3. Quantity" + " ".repeat(2));
                tableToUpdate.addCell(" ".repeat(2) + "4. Price" + " ".repeat(2));
                tableToUpdate.addCell(" ".repeat(2) + "5. Back to menu" + " ".repeat(2));
                System.out.println(tableToUpdate.render());
                try {
                    System.out.print("Choose option (1-5) : ");
                    int option = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1 -> {
                            try {
                                System.out.print("Enter new product name: ");
                                String newProductName = sc.nextLine();
                                System.out.print("Enter new quantity: ");
                                Integer newQuantity = Integer.parseInt(sc.nextLine());
                                System.out.print("Enter new price: ");
                                Integer newPrice = Integer.parseInt(sc.nextLine());

                                product.setName(newProductName);
                                product.setQty(newQuantity);
                                product.setUnitPrice(newPrice);

                                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                                updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                                updatedTable.addCell(" Unit price    : " + newPrice + " ".repeat(10));
                                updatedTable.addCell(" Qty           : " + newQuantity + " ".repeat(10));
                                updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                                System.out.println(updatedTable.render());

                                System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                                String options = sc.nextLine();
                                switch (options) {
                                    case "y", "Y" -> {
                                        Write.stockManagementArrayList.add(productToUpdate);
                                        System.out.println("Product added successfully.");
                                    }
                                    case "n", "N" -> System.out.println("Product is not added");
                                    default -> System.out.println("Invalid options.");
                                }
                            } catch (Exception exception) {
                                System.out.println(exception.getMessage());
                            }
                        }


                        case 2 -> {
                            try {
                                System.out.println("Enter new product name : ");
                                String newProductName = sc.nextLine();
                                product.setName(newProductName);
                                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                                updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                                updatedTable.addCell(" Unit price    : " + product.getUnitPrice()
                                        + " ".repeat(10));
                                updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                                updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                                System.out.println(updatedTable.render());
                                System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                                String options = sc.nextLine();
                                switch (options) {
                                    case "y", "Y" -> {
                                        Write.stockManagementArrayList.add(productToUpdate);
                                        System.out.println("Product added successfully.");
                                    }
                                    case "n", "N" -> System.out.println("Product is not added");
                                    default -> System.out.println("Invalid options.");
                                }
                            } catch (Exception exception) {
                                System.out.println(exception.getMessage());
                            }
                        }
                        case 3 -> {
                            try {
                                System.out.println("Enter new product Price : ");
                                Integer newProductPrice = Integer.parseInt(sc.nextLine());
                                product.setUnitPrice(newProductPrice);
                                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                                updatedTable.addCell(" Name          : " + product.getName() + " ".repeat(10));
                                updatedTable.addCell(" Unit price    : " + newProductPrice + " ".repeat(10));
                                updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                                updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                                System.out.println(updatedTable.render());
                                System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                                String options = sc.nextLine();
                                switch (options) {
                                    case "y", "Y" -> {
                                        Write.stockManagementArrayList.add(productToUpdate);
                                        System.out.println("Product added successfully.");
                                    }
                                    case "n", "N" -> System.out.println("Product is not added");
                                    default -> System.out.println("Invalid options.");
                                }
                            } catch (Exception exception) {
                                System.out.println(exception.getMessage());
                            }
                        }
                        case 4 -> {
                            try {
                                System.out.println("Enter new product Price : ");
                                Integer newProductQty = Integer.parseInt(sc.nextLine());
                                product.setQty(newProductQty);
                                Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                                updatedTable.addCell(" ID            : " + idToUpdate + " ".repeat(10));
                                updatedTable.addCell(" Name          : " + product.getName() + " ".repeat(10));
                                updatedTable.addCell(" Unit price    : " + product.getUnitPrice() + " ".repeat(10));
                                updatedTable.addCell(" Qty           : " + newProductQty + " ".repeat(10));
                                updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                                System.out.println(updatedTable.render());
                                System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                                String options = sc.nextLine();
                                switch (options) {
                                    case "y", "Y" -> {
                                        Write.stockManagementArrayList.add(productToUpdate);
                                        System.out.println("Product added successfully.");
                                    }
                                    case "n", "N" -> System.out.println("Product is not added");
                                    default -> System.out.println("Invalid options.");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case 5 -> System.out.println("Back to menu : ");
                        default -> throw new IllegalStateException("Unexpected value: " + option);
                    }


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        }
    }
    public class Next {
        public static int nextFunction(int currentPage , int rowsPerPage) {
            int totalPages = (int) Math.ceil((double) Write.stockManagementArrayList.size() / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                Display.displayFunction(currentPage, rowsPerPage);
            } else {
                System.out.println("You are already on the last page.");
            }
            return currentPage;
        }
    }
    public class Previous {
        public static int previousFunction(int currentPage, int rowsPerPage) {
            if (currentPage > 1) {
                currentPage--;
                Display.displayFunction(currentPage, rowsPerPage);
            } else {
                System.out.println("You are already on the first page.");
            }
            return currentPage;
        }
    }
    public class Read {
        public static void readFunction() {

            Scanner sc = new Scanner(System.in);
            boolean isFound = false;
            System.out.print("Read by ID : ");
            Integer productID = Integer.parseInt(sc.nextLine());
            for (StockManagement stockManagement : Write.stockManagementArrayList) {
                if (stockManagement.getId().equals(productID)) {
                    Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                    table.addCell(" ID            : " + productID + " ".repeat(10));
                    table.addCell(" Name          : " + stockManagement.getName() + " ".repeat(10));
                    table.addCell(" Unit price    : " + stockManagement.getUnitPrice() + " ".repeat(10));
                    table.addCell(" Qty           : " + stockManagement.getQty() + " ".repeat(10));
                    table.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                    System.out.println(table.render());
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Product with ID : " + productID + " is not found");
            }
        }
    }


    public class Help {
        public static void helpFunction() {
            System.out.println("! 1." + " ".repeat(5) + "Press" + " ".repeat(5) + "* Display all record of products");
            System.out.println("! 2." + " ".repeat(5) + "Press" + " ".repeat(5) + "w: Add new product");
            System.out.println("! " + " ".repeat(17) + "w →#proname-unitprice-qty: sortcut for add new product!");
            System.out.println("! 3 " + " ".repeat(5) + "Press" + " ".repeat(5) + "r: read Content any content");
            System.out.println("! " + " ".repeat(17) + "r#proId : shortcut for read product by Id");
            System.out.println("! 4." + " ".repeat(5) + "Press" + " ".repeat(5) + "u: Update Data");
            System.out.println("! 5." + " ".repeat(5) + "Press" + " ".repeat(5) + "d: Delete Data");
            System.out.println("! " + " ".repeat(17) + "d#proId : shortcut for delete product by Id");
            System.out.println("! 6." + " ".repeat(5) + "Press" + " ".repeat(5) + "f: Display First Page");
            System.out.println("! 7." + " ".repeat(5) + "Press" + " ".repeat(5) + "p: Display Previous Page");
            System.out.println("! 8." + " ".repeat(5) + "Press" + " ".repeat(5) + "n: Display Next Pages");
            System.out.println("! 9." + " ".repeat(5) + "Press" + " ".repeat(5) + "l: Display last page");
            System.out.println("! 10." + " ".repeat(4) + "Press" + " ".repeat(5) + "s: Search products by name");
            System.out.println("! 11." + " ".repeat(4) + "Press" + " ".repeat(5) + "h: help");

        }
    }

    public class Display {
        public static int currentPage = 1;
        public static int rowsPerPage = 2;

        public static void displayFunction(int currentPage, int rowsPerPage) {
            int productList = Write.stockManagementArrayList.size();
            int start = (currentPage - 1) * rowsPerPage;
            int end = Math.min(start + rowsPerPage, productList);
            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Unit Price");
            table.addCell("Qty");
            table.addCell("Imported Date");
            for (int i = start; i < end; i++) {
                StockManagement stockManagement = Write.stockManagementArrayList.get(i);
                table.addCell(String.valueOf(stockManagement.getId()));
                table.addCell(String.valueOf(stockManagement.getName()));
                table.addCell(String.valueOf(stockManagement.getUnitPrice()));
                table.addCell(String.valueOf(stockManagement.getQty()));
                table.addCell(String.valueOf(stockManagement.getDate()));
            }
            System.out.println(table.render());
            System.out.println("o" + "~".repeat(70) + "o");
            int totalPages = (int) Math.ceil((double) productList / rowsPerPage);
            System.out.println("Page " + currentPage + " of " + totalPages + " ".repeat(40) + "Total records : " + productList);
            System.out.println("o" + "~".repeat(70) + "o");
        }

    }
}
