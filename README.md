# gestion-stock

==================
# Installation Guide 
==================

- Install server management services (easyphp or wampServer or apache with phpmyadmin)
- Run server service (ex ubuntu-apache : sudo service apache2 start)
- Go to phpmyadmin (127.0.0.1), create database with this name : stock_magasin
- Inside file "stock_magasin.sql" copy all sql lines
- Go to phpmyadmin and go to your new database
- Go to SQL tab
- Paste sql lines in the field and execute

- Install java runtime 11
- Open xml file in [DI4][DERENNE-JAGOREL]\Application\src\META-INF\persistence.xml
- Set you login and your password in those lines between value quotes :
	<property name="javax.persistence.jdbc.user" value="root"/>
    <property name="javax.persistence.jdbc.password" value=""/>
- Run Stock_Management.jar with java 11



==========
# User Guide
==========

By default you have somes users
You can login with administrator user (login : admin / password : admin)
With this count, you see all user and manage all what you want
You can manage article and manage users
The application is simple and easy to use, but you can find more informations in "cahier des specifications.pdf"