# SampleAlbumApp

Sample app that displays albums for a given query from the Imgur API. Refer below highlighting points for deeper info:

1. This is developed using MVVM architecture.
2. MVVM inherently improves testability and maintainability and various other factors.
3. All the basic constants are defined in main package constant file.
4. Project initialization like dependency injection and image loader configuration is done in main application class.
5. All the API related code is put inside data package.

## Library used

Sample app has used various third party libraries to miniminze the development effors and maximize the standard code.

###### AndroidX Libries
All general libraries like kotlin, appcompat, recyclerview, and constraint layout (not really required for this flat UI hierarchy)

###### Kotlin koin
Code injection library for easy object creation and dependency injection

###### Retrofit and okhttpinterceptor
These libraries are used to call APIs

###### kotlin-coroutines
Kotlin extension for calling APIs in worker threads

###### Converter-moshi
JSON serializer/deserializer for retrofit

###### Unversal image loader
Library for loading images. This is pure java implementation and seem small footprints

###### TouchImageView
Used to display image which required pinch and zoom functionality


