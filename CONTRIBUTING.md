# Contributing to Celestial

When contributing to Celestial, we require that you follow these guidelines:

1. [Questions](#questions)
2. [Bug Reports](#bug-reports)
3. [Features Requests](#features)
4. [Commit Guidelines](#commit-guidelines)
5. [Pull Request Guidelines](#pull-request-guidelines)
6. [Code Quality Requirements](#code-quality)
   1. [Rules](#rules)
   2. [Style](#style)

## Questions

For questions, please go to the discussions. 

## Bug Reports

If you have found a bug please submit an issue or submit a pull request with the fix. 

## Features

To request a feature, please submit an issue where it can be discussed. If you would like to 
implement the feature submit a detailed issue where it can be discussed beforehand.

## Commit Guidelines

[Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)

## Pull Request Guidelines

TODO

## Code Quality

### Rules

- Use `java.util.Optional` instead of null unless returning null if argument is null
- No `return null;` statements
- Methods and parameters must be annotated with `org.jetbrains.annotations.NotNull` or 
  `org.jetbrains.annotations.NotNull`
- Classes not made for public consumption should be marked with 
  `@org.jetbrains.annotations.ApiStatus.Experimental`
- Public packages, classes, methods, inner classes and public or protected fields must have 
  javadocs. 
- All methods except void or simple getter methods (only return statement) should be annotated with 
  `com.google.errorprone.annotations.CanIgnoreReturnValue` or 
  `com.google.errorprone.annotations.CheckReturnValue`

### Style

We use a modified version of [AOSP](https://source.android.com/setup/contribute/code-style).

Notable changes:

- Single blank line before method body
- [Javadoc Style](https://www.oracle.com/au/technical-resources/articles/java/javadoc-tool.html)
    - Use grammar in tag description

The code style is set up for Intellij. Javadocs have to be formatted manually. 
