# kinsa
Kinsa mini project

Assumptions / shortcuts:
 1) Defined project properties in a Java class.
      i) Google Decoding API key
     ii) locations.csv file provided by Kinsa

 Please note, before compiling code you need to modify Locations.csv full path in Java class file :  com.kinsa.rest.data.ProjectProperty

 2) I have used IDE IntelliJ to code this project. All tests passes however I have not fixed running tests from command line maven.

 3) Used JDK 1.7 to compile

Steps :

1) Download code (GitHub or zip file)
2) from command line ( Windows or  Cygwin on Windows  or Linux shell)
3) mvn clean compile
4) mvn exec:java


 Run tests :
   I have run following tests using Chrome Extension : Advanced REST Client
   i)  List all coffee shops
       http://localhost:8080/kinsa/coffeeshops/all
       Method : Get
       Content-Type: application/json

   ii) Update coffee shop # 2
       http://localhost:8080/kinsa/coffeeshops/2
       Method : Get
       Content-Type: application/json

   iii) Delete coffee shop # 55
       http://localhost:8080/kinsa/coffeeshops/delete/55
       Method : Delete

   iv)  Update coffee shop # 56
        http://localhost:8080/kinsa/coffeeshops/update
        Method : Put
        Content-Type: application/json

        Payload :
        {
        "id": 56,
        "name": "Umesh Prasad : Chapel Hill Coffee Co.",
        "address": " 670 Commercial St",
        "latitude": 37.794096040757196,
        "longitude": -122.40423200906335
        }

    v)  Add a new Coffee Shop
        http://localhost:8080/kinsa/coffeeshops/add
        Method : Post
        Content-Type: application/x-www-form-urlencoded
        Payload : address=520 Logue Avenue, Mountain View, CA 94043 , USA&name=Umesh Prasad Work - Coffee Shop&latitude=37.39910560&longitude=-122.04906790

    vi) Find closest coffee shop. Input an invalid address.
        http://localhost:8080/kinsa/coffeeshops/closest?address="invalidAddress"
        Method : Get
        Content-Type: Content-Type: application/x-www-form-urlencoded

    vi) Find closest coffee shop. Input 'Guerrero' address.
        http://localhost:8080/kinsa/coffeeshops/closest?address=252+Guerrero+St%2C+San+Francisco%2C+CA+94103%2C+USA
        Method : Get
        Content-Type: Content-Type: application/x-www-form-urlencoded

    vi) Find closest coffee shop. Input 'Mission' address.
        http://localhost:8080/kinsa/coffeeshops/closest?address=%22535+Mission+St.%2C+San+Francisco%2C+CA%22
        Method : Get
        Content-Type: Content-Type: application/x-www-form-urlencoded





Please email me at umeshprasad@gmail.com if you run into any issue.

