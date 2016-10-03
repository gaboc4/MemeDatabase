import praw

user_agent = "RateMemeScraper 1.0 by u/ethmarch"
r = praw.Reddit(user_agent=user_agent)

# This can be replicated for any subreddit. Simply call
# r.get_subreddit(*desired subreddit name*)
me_irl = r.get_subreddit('me_irl')
meirl = r.get_subreddit('meirl')
bpt = r.get_subreddit('BlackPeopleTwitter')


def top_ten(subreddit, results):
    results.seek(0, 2)
    for submission in subreddit.get_top(limit=10):
        results.write(str(submission.score) + ',' + submission.url)
        results.write('\r')

# Set the first parameter in open() to the file you want to
# to write the results to. THIS FILE WILL BE OVERWRITTEN EVERY
# TIME THE SCRIPT IS RUN. PROCEED WITH CAUTION.
file = open('memeScores.csv', 'w')

# Call top_ten(*subreddit from above*, file) to get the top 10
# posts and their scores.
top_ten(me_irl, file)
top_ten(meirl, file)
top_ten(bpt, file)
file.close()