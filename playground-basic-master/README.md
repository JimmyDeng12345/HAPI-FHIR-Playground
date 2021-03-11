This is my solution to the basic tasks.

Some notes:
  1. When a patient has more than one given name, those names are diveded by "," and stored in an array.
     exp: [ "John\",\"Joe" ] instead of ["John" , "Joe"]
  2. Tried the sort() method in the query with SortSpec as Patient.GIVEN.getParamName(), which didn't work I thought it would. Will investigate furthur.
