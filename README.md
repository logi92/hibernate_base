# hibernate_base
Base principals of hibernate in little project
It is my little project to improve my skills in Hibernate Framework. I used here Hibernate Framework to connect with DB and PostgreSQL as DB. 
I created this console app which can do such operations like :
- Show All Products 
- Show All Consumers
- Show Products By Consumer 
- Find Consumer By Products
- Remove Consumer
- Remove Product
- Responsibility to buy any product by any consumer

How to use: 

It is simple console application. Before you, you must to change connection settings to your DB. You can do it in hibernate.cfg.xml file.
Before you run this application, i must warning you , that inside this app use some sql script to drop and create all necessary tables:
- consumer
- product
- consumer_product

If you want to exclude this possibility, you should comment out the line "Repository.prepareBD();" in ShopApp.java.
When you run this application main loop will ask you "what you want to do ?". 
You can write operations like :
- showAllProducts 
- showAllConsumers
- showProductsByConsumer consumer_name
- findConsumersByProductName product_name
- removeConsumer consumer_name
- removeProduct product_name
- buy consumer_name product_name
- exit 
