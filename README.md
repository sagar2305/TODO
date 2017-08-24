# Pre-work - *TODOist*

TODOist is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Sagar Mutha

Time spent: 4 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [x] Add support for marking tasks complete!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/G1bddfN.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** The activity lifecyle in android is very well defined and doesn't need a lot of work getting used to. The layouts editor is very different compared to the iOS storyboard. The iOS storyboard allows you to build the complete app flow within it whereas the android layout editor does not. Also I did not find the android layout editor very user friendly in building the UI. I preferred to use the xml layout to build it.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** The adapter in android is used to hold the data that is used to populate the views. It is the one responsible for showing content on the list and also decides how the data must be shown. The adapter is important so that the views do not hold any data or business logic and only worry about the presentation.

The convertView parameter is used to reuse old android View objects. The adapter calls getView on each row of the ListView. The convertView  are initially null and are created to fill up the views visible on the screen.Once these objects go out of the screen, they are reused. This helps improve the performance of the app.

## Notes

Describe any challenges encountered while building the app.


## License

    Copyright 2017 Sagar Mutha

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.