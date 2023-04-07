# Online shop simulator
## Introduction
The main goal of this project is to design an online shop providing e-commerce services, some real-world examples are *Amazon* and *Alibaba*.<br>
Scince the project contain *FXML* files, you need to download the appropriate JDK (which is *JDK 8* in this project) to run the code.
## Prerequisites
- appropriate JDK (JDK 8 is used, so JavaFX could be run with the code)
- NetBeans (I used version 8 to build this project)
- SQLite
- Git as version control system
## Features
- Customer<br>
In short, a customer can ***search*** for a product name (e.g. iphone 14) or a seller name (e.g. Apple) or ***order by looking at all the products***, ***look at his cart*** and ***deposit money*** to his wallet. <br>
Finally, a customer can ***check his profile***.<bt>
***Note***: When adding money to wallet, a request is sent to the admins. After an admin reviews the request, he confirms to add the money to the customer wallet and then, the database will be updated and the money will be added to the wallet.
![customer menu](https://raw.githubusercontent.com/Shyshfa/online-shop-simulator/develop/pics/customer-menu.PNG)
- Seller<br> 
***Adding products*** and ***checking products lists*** is the two important things a seller can do. Other than that a seller can ***withdraw money*** from his wallet.<br>
A seller can ***check his profile***, just like a customer. 
![seller menu](https://raw.githubusercontent.com/Shyshfa/online-shop-simulator/develop/pics/seller-menu.PNG)
- Admin<br>
Admins can ***confirm deposits/withdraws***, ***add other admins*** and ***see all users list***.
![admin menu](https://raw.githubusercontent.com/Shyshfa/online-shop-simulator/develop/pics/admin-menu.PNG)

## Technologies Used
This project is written entirely with *Java*, and the GUI is designed using [*JavaFX*](https://en.wikipedia.org/wiki/JavaFX) and *Scene Builder* (Scene Builder generates FXML, an XML-based markup language that enables users to define an application's user interface, separately from the application logic. You can also open and edit existing FXML files authored by other users.)<br>
Besides that, the database related stuff are all done using *SQLite*.


Getting started: Provide instructions on how to download, install, and run your project. Include any , setup steps, configuration options, or environment variables that are necessary.

## Usage:
(To run the project, clone this repo and open the `Digishop` folder in your IDE of choice and press the run button)
To start using the application, you can either create an account through the `sign up` button or use one of the pre-defined accounts creared for ease of use:<br>
![start menu](https://raw.githubusercontent.com/Shyshfa/online-shop-simulator/develop/pics/start.PNG)
| Username | Password | Defined Role |
| :---: | :---: | :---: |
| customer | customer | customer |
| seller | seller | seller |
| admin | admin | admin |

After you login to your accaount, a menu will appear showing what you can do.<br>
As a customer, you can 

---------------
## Resources
### Video Playlists
- [JavaFX tutorial](https://www.youtube.com/watch?v=_7OM-cMYWbQ&list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev)
- [JavaFX TableView Tutorials](https://www.youtube.com/watch?v=97nHAyMktTE&list=PL2EKpjm0bX4IWJ1ErhQZgrLPVgyqeP3L5&index=1)
### Websites
- [Online SQLite browser](https://extendsclass.com/sqlite-browser.html)
- [Oracle](https://www.oracle.com/)
- [Website to download scene builder ](https://gluonhq.com/products/scene-builder/)
### Aplications
- [TablePlus](https://tableplus.com/)
