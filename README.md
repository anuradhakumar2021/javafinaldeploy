# javafinaldeploy
Task 9: Implementing the REST API
Description
In this task, we'll implement our REST API so you can perform the CRUD(Create, Read, Update and Delete) operations over your data model using the HTTP protocol.

Walkthrough
Step 1: Implementing the ItemService
Useful Resources for this step
Spring Beans and Dependency Injection
In this step, we'll define our Service interface and create an implentation that handles the interaction with MySQL Database. An interface is a structure or syntax that allows software developers to define a component's behavior without worrying about the implementation.

Create a new package in your Spring Boot project called service

Create the ItemService interface with the functions needed to implement the Items CRUD:

     public interface ItemService
     {

         Item save( Item item );

         boolean delete( int itemId );

         List<Item> all();

         Item findById( int itemId );

     }
Create an implementation of the ItemService called ItemServiceMySQL and inject the ItemRepository.

        public class ItemServiceMySQL implements ItemService
        {
            private final ItemRepository itemRepository;

            public ItemServiceMySQL(@Autowired ItemRepository itemRepository )
            {
                this.itemRepository = itemRepository;
            }

            @Override
            public Item save( Item item )
            {
                //TODO implement this method
                return null;
            }

            @Override
            public void delete( int itemId )
            {
                //TODO implement this method
            }

            @Override
            public List<Item> all()
            {
                //TODO implement this method
                return null;
            }

            @Override
            public Item findById( int itemId )
            {
                //TODO implement this method
                return null;
            }
        }
Implement the methods so you persist and retrieve your data using the ItemRepository.

Annotate the ItemServiceMySQL with @Service so it can be injected into the ItemController

Test Your Code!
Now is a good chance to test your code, follow the steps below:

Inject the ItemService inside the ItemController
Add a breakpoint inside the getItems function on the line 24 of the ItemController.
Run your project on Debug Mode and open http://localhost:8080/item on your browser.
Expected Result You should see that the ItemService variable is instantiated with the ItemServiceMySQL

Step 2: Connecting your ItemController with the ItemService
Now that we have defined the ItemService behavior and created an implementation ItemServiceMySQL - we can use this service to implement our REST API methods to fulfill the CRUD operations.

Inject ItemService inside the ItemController.

Modify the endpoint to retrieve the list of items to be /item/all and change the funciton implementation so it calls the itemService.all()

Create a new package inside the controller called dto for the Data Transfer Objects, these are Java classes used to map the JSON data structure sent and received by the REST controller.

Add a new class called ItemDto inside the dto package.

public class ItemDto
{

    private String name;

    private String description;

    private String imageUrl;

    public ItemDto( String name, String description, String imageUrl )
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }
}
Implement a new function inside the ItemController to create a new Item using the @PostMapping annotation and the @RequestBody annotation to receive an ItemDto as parameter on the POST request.

Call the itemService.save to persist the item received in the request.

    @PostMapping
    public Item save( @RequestBody ItemDto itemDto )
    {
        return itemService.save( new Item( itemDto ) );
    }
Implement a new function inside the ItemController to retrive a specifc item using the item Id

    @GetMapping("/{id}")
    public Item findItemById( @PathVariable Integer id ){
        return itemService.findById( id );
    }
Implement the remaining CRUD methods using the @PutMapping to update an item and @DeleteMapping to delete an item.

    @PutMapping( "/{id}" )
    public Item update( @RequestBody ItemDto itemDto, @PathVariable Integer id )
    {
        Item item = itemService.findById( id );
        item.setName( itemDto.getName() );
        item.setDescription( itemDto.getDescription() );
        item.setImageUrl( itemDto.getImageUrl() );
        return itemService.save( item );
    }

    @DeleteMapping( "/{id}" )
    public void delete( @PathVariable Integer id )
    {
        itemService.delete( id );
    }
Results
Start your server and test your Item's endpoint using any HTTP client like Postman - Example of how to send requests in postman



Example

Step 1: Fork Repo: https://github.com/reanderson89/jwt-courses-deploy
Step 2: Clone to your computer.
Step 3: Go to Heroku https://www.heroku.com/
Step 4: Create Heroku account: https://signup.heroku.com/
Step 5: Download & Install Heroku CLI: https://devcenter.heroku.com/articles/heroku-cli#download-and-install
	Mac Users: Use brew install given on installation page, using this line of code

		 brew tap heroku/brew && brew install heroku 

	Window Users: Follow this video- https://www.youtube.com/watch?v=fpEgZi3_RI4

Step 6: Create 2 files in IntelliJ.

   File 1: Text file called ‘ Procfile ‘ with a capital P with NO EXTENSION in the root directory
	Inside the file paste the following:
 
  		web: java -jar build/libs/jfs_jwt-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod	

   File 2:  Create system.properties in the root directory
	Inside the file type the following: 

		java.runtime.version=11

Step 7: Commit these changes to GitHub.
Step 8: Next, we will create the app in Heroku. Make sure you’re in the main branch of your repo. Type in the following command in your terminal: 

		heroku create

Step 9: Go to Heroku on your browser and refresh the page. Click on your project name. 
Step 10: In the “Overview” tab, click on “Configure Add-ons”.


Step 11: In “Configure Add-ons” search for ‘ ClearDB MySQL ‘ and select that option. 


Step 12: Choose ‘Ignite-Free’ plan then click ‘Submit Order’
Step 13: Input payment information- you will not be charged.
Step 14: Go back to terminal & make sure you’re in the main branch of your repo and type the command:

	git push heroku main

Step 15: Go back to Heroku, click on “More” on top right of the page and click on “View Logs” to see your application being built. 
Step 16: Connect MySqlWorkbench to your deployed applications database
In the browser in your heroku application go to Settings, then click on “Reveal config Vars”
Grab the value for CLEARDB_DATABASE_URL for example it will look something like this: (THIS IS AN EXAMPLE, USE YOUR OWN URL. DO NOT USE THIS ONE!!!) mysql://bbf725391c65b3:f8be78ec@us-cdbr-east-05.cleardb.net/heroku_32ac2a51de2dcbd?reconnect=true
Username: bbf725391c65b3
Hostname: us-cdbr-east-05.cleardb.net
Password (click on “store in keychain when creating connection): f8ke78ec
Open MySqlWorkbench and get to the home screen. 
Click the “+” symbol just to the right of “MySql Connections”
Add the above information from your CLEARDB_DATABASE_URL into their respective areas and then click test connection.
STEP 17: Test the routes using postman. Make sure to grab the url from heroku.
Make a POST request, select body, raw, click text and select JSON
The body of the post should contain this object
{
"username": "user@mail.com",
"password": "password"
}

After you get the accessToken back from your post then create a new tab to POST a course using this object. Make sure that you add the “Authorization” key to the header of the body and set the value to: Bearer yourJWT
 {
   "name":"First Course",
   "objectives": "This is the first course"
}

After you POST a new course, switch your request to a GET request and you should get a 500 Internal Server error. Check the error in your logs in heroku.
check the logs in the browser on your heroku application to find the error. Fix the error in your code and then commit the changes. After committing the changes do another “git push heroku main” to update the server on heroku. (HINT: it has something to do with the Course class.)
Try to GET all courses again, if you get a server 200 response then it was successful and you should see the courses you added.
