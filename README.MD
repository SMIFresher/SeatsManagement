#SEATMANGEMENT
Work Place Management created by SMI_Freshers. 

________About the project__________ 

This project is to manage the placement of Seats, Systems and Employees in a company

____Description____ 
 	Now a days Work place management is big challenge for IT companies for allocation, deallocation, reallocation of system, employees and finding the empty places.In order to overcome the above, we created this project to know/see the empty seats , allocated seats, deallocated seats, reallocated seats.

____Target Audience____

The application is developed with Company HRs in mind, to ease their workload of assigning place for seats, systems and employees within an organisation. The application achieves this by providing visual manipulation facilities of the above mentioned entities in a 2D floorplan.

______Technologies Used______ 

1 - Eclipse - Oxygen.3a Release (4.7.3a)
2 - JDK 1.8 
3 - Angular js v1.6
4 - MySql v5.7
5 - Apache TomCat v8.0


 ____Configuration for working in Eclipse____ 

1) Create a maven project using the pom.xml in location /seatmanagement/pom.xml
2) Add ‘test’ and ‘resources’ folders as source folders in the newly created project
3) Build project using maven command “clean compile install”
4) Force update snapshot if errors remain
5) Run mysql queries in file “seatmanagement-query.sql” located in WEB-INF
6) Database configuration can be done in “build.properties” file
7) The profiles.active property should be set to “development” to enable Spring development 	profile. Another profile “test” is used 	to enable spring test profile. The application runs optimally in “development” profile.
8) Add module to server
9) For image storing and retreiving two configurations have to be done
		a)set value for image.path property in build.properties 
			(eg. image.path=D:\\imageUpload\\)
		b) an additional module needs to be configured in the server
			- option : add “External Web Module”
				- Document Base : <Same as image.path property in build.properties file>
				- path : /images
10) Start the server and the application is deployed
11) Hrs and Leads can access the application
		- For HRs the username is "HR" and password is any non-empty string
		- For Leads the username is "Lead" and password is any non-empty string

________Contributors__________ 

1) Karthika M - karthika.m@mookambikainfo.com
2) Sithaara S - sithaara.s@mookambikainfo.com
3) VenkataNarayanan V - venkatanarayanan.v@mookambikainfo.com
4) Selvamani G - selvamani.g@mookambikainfo.com
5) Prithivi E - prithiviraj.m@mookambikainfo.com
6) Harshanaa R - harshanaa.r@mookambikainfo.com
7) Saieswari TK - saieswari.k@mookambikainfo.com
8) Karthika K - karthika.k@mookambikainfo.com
9) Aathitya Prabu AS - aathityaprabu.a@mookambikainfo.com
10) Vijayakumar S - vijayakumar.s@mookambikainfo.com


_Thanks_