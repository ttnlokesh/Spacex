# Spacex
Architecture and design pattern used is MVVM(Model View Viewmodel).
Adavantage for MVVM
  1) ViewModel does not hold any kind of reference to the View.
  2) Many to 1 relationship exist between View and ViewModel.
  3) No triggering methods to update the View.

Project Highlights:-
Project has two screen list and details screen. On list screen user can sort by mission name and filter either by launch success or lauch fails. List showing un-sorted way first.
User can sort data either before apply filter or after apply filter. For improving code quality i have wirtten unit test cases. For UI seperation i have used data binding.
