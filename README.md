android-stackview
=================

We have created a tinder-like StackView to show a stack of cards. Cards can be swiped away either horizontally or vertically. 


![alt tag](https://raw.githubusercontent.com/edcast-inc/android-stackview/master/screenshots/sample1.gif)


The code is simple. You simply need to extend AbstractCardsStackView and set the orientation and adapter:

```java
MyCardStackView sv = (MyCardStackView)findViewById(R.id.my_stack_view);
sv.setOrientation(SwipeTouchListener.Orientation.Vertical);
sv.setAdapter(new CardAdapter());
View emptyView = inflater.inflate(R.layout.empty_page, null);
sv.setEmptyView(emptyView);
```

