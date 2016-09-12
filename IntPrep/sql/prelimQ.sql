'''
Input{userId, LoginTime}
Output(UserId, LoginTime, SessionId).

Note that the session Id is an integer, and when a user login after 30 minutes of its previous login, you will give him/her next sessonid.
new user, will always get next sessionId.

Example
Input
1	9:00 AM
2	9:10 AM
1	9:25 AM
30	12:34PM
23	3:09 PM

Output
UserId	LoginTime	SessionId
1	9:00 AM	1
2	9:10 AM	2
1	9:25 AM	1
30	12:34PM	3
23	3:09 PM	4
'''

SELECT userId, MAX(LoginTime) AS latestLogin
INTO latestLogins
FROM Input;

SELECT userId, MAX(SessionId) AS SessionId
INTO OutputX
FROM Output;

SELECT userId, ((latestLogin IS NULL)?1: ((latestLogin - MAX(LoginTime)) < 30)?D.SessionId:D.SessionId + 1) AS SessionId
FROM Input AS A
    LEFT OUTER JOIN latestLogin B ON (A.userId == B.userId)
    LEFT OUTER JOIN OutputX AS D ON (A.userId == D.userId)
WHERE LoginTime < (SELECT latestLogin
                    FROM latestLogins AS C
                    WHERE C.userId == A.userId
                    )
;


currentSession =
    SELECT  userId
            , MAX(SessionId)
    FROM Output
    WHERE UserId == @newIncomingUser
    GROUP BY userId;

newRow =
    SELECT A.userId, A.LoginTime
            , ((B.SessionId IS NOT NULL)?1:
                        ((DateTime.Now() - MAX(A.LoginTime)) > 30)? B.SessionId + 1: B.SessionId) AS SessionId
    FROM Input AS A
            LEFT OUTER JOIN currentSession AS B ON ()
    WHERE userId == @newIncomingUser
    GROUP BY userId;
