# JavaTwitterCrawler
A simple Twitter Crawler in Java.

This is a simple crawler solution, developed in Java, for academic research purposes on testing the Twitter rate limit on the Stream API, using the library Twitter4J.

- It reads the Oauth keys and query settings (language, keywords) from file ("input" folder).
- Register your app on dev.twitter.com to get its oauth keys so you can set the oauthKeys.config file.
- The results can be obtained per run (useful if you would like to schedule parallel runs) and are stored in the "output" folder.
- The tweets.txt file is just to check the tweet format and properties.
- No persistence module is set on this version yet. The tests are being done so the best DB can be chosen for storage of so many data.




