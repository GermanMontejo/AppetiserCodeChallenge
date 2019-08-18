# AppetiserCodeChallenge
This app was created as part of the Coding Challenge for Appetiser.

## Overview
This app demonstrates a simple Master/Detail Flow. It fetches data from the Itunes' search API with the query parameters:
term, country, and media. The values for these query parameters have been hardcoded, with the term = "star", country = "au", and media = "movie". The app follows the MVVM software architectural pattern. The user also has the capability to sort the list's result by tapping the Floating Action Button. Doing so, will toggle the results in ascending/descending order.

The app uses the following technologies:
1. Glide - for image loading and caching
2. RecyclerView - for rendering list of itunes tracks retrieved from the API.
3. Retrofit - for network requests
4. Room - for persistent data storage (on top of sqlite).
5. ViewModel - used to manage the UI's data, in a lifecycle-conscious way.

MVVM was the chosen architectural style of designing the whole app due to one main reason:
To achieve a cleared separation of UI and application logic without introducing complexity and boilerplate code.

With this architectural style, I was able to delegate specific tasks to different components: Model, View, ViewModel.
For the Model, this is where I placed my app's data and it also has the Repository - the class which serves as the single source of truth for the ViewModel. Also, for the Model, this is where I placed my Room (db) and API (network) related-classes and interface.

The ViewModel acts as the mediator between the View and the Model. I designed the app in such as way that no direct communication must happen between the View and the Model. Any changes to the data must be "observed" by the View.

The View's responsibility is only to display the data. As such, it observes for any updates to the data, e.g. data being fetched from the API, or data being loaded from the local db through Room.
