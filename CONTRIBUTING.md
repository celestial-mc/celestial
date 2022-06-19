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

[//]: # (TODO)
TODO

## Code Quality

### Rules

- Two kotlin extension methods must be made everytime `java.lang.reflect.Class` is used in the 
public api. One must use `kotlin.reflect.KClass` and the other must use reified type parameters.

### Style

We use a modified version of [AOSP](https://source.android.com/setup/contribute/code-style).

Notable changes:

- Single blank line before method body
- [Javadoc Style](https://www.oracle.com/au/technical-resources/articles/java/javadoc-tool.html)
    - Use grammar in tag description

The code style is set up for Intellij. Javadocs have to be formatted manually. 
