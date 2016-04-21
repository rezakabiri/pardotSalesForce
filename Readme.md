This folder contains all the files and folders to automate below procedure:

The project consists of three main Java files. 
"logInPage.java" and "mainPage.java" are two pages factories. 
"pardotTestSuite.jva" is the TestNG file that you should run to see the results.
By running this file the content in the test-output folder will be updated. 
It should be noted that due to the issue with few WebElements Steps#6, 7, and 8 remained incompleted. 
(I have highlighted the issues, along with all the possible solutions that may help resolve them, in the comments.)

1)  Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012)
2)  Create a list with a random name (Marketing > Segmentation > Lists)
3)  Attempt to create another list with that same name and ensure the system correctly gives a validation failure
4)  Rename the original list
5)  Ensure the system allows the creation of another list with the original name now that the original list is renamed
6)  Create a new prospect (Prospect > Prospect List)
7)  Add your new prospect to the newly created list
8)  Ensure the new prospect is successfully added to the list upon save
9)  Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so you will not actually be able to send the email.  This is okay.
10) Log out