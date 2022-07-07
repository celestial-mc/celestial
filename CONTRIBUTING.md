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

- Commits should be all lowercase unless referencing a something that has casing
- Commits should be in present tense
- No full stop (.) at the end of the commit message

### Commit types

```
build: A change that affects the build. 
ci: A change that affects CI configuration. 
docs: Documentation changes. 
feat: A code change that adds functionality. 
fix: A code change that fixes a bug. 
perf: A change that improves performance. 
refactor: A code change that neither fixes a bug nor adds a feature. 
style: A change that fixes or improves code style. 
test: Add, remove, or edit tests. 
```

### Scopes
```
gradle: changes related to gradle. 
gha: change related to github actions. 
qodana: changes to qodana configuration. 
codecov: changes to codecov configuration. 
release: changes that are required before or after a release. 
deps: updates to dependencies. 
readme: changes to the readme
contributing: changes to contributing guidelines
```

## Pull Request Guidelines

TODO

## Code Quality

### Rules

- Prefer `java.lang.util.Optional` over `null`
- Classes not made for public consumption should be marked with 
  `@org.jetbrains.annotations.ApiStatus.Experimental`

### Style

We use a [AOSP](https://source.android.com/setup/contribute/code-style) style.

The code style is set up for Intellij. Javadocs have to be formatted manually. 
