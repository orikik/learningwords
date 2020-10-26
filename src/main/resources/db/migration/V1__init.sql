CREATE TABLE IF NOT EXISTS WORDS(
	ID 		    INTEGER		NOT NULL,
	WORD 		TEXT		NOT NULL,
	DESCRIPTION	TEXT		NOT NULL,
	WORD_TYPE   TEXT		NOT NULL,

    UNIQUE (WORD, WORD_TYPE),

	PRIMARY KEY (ID)
);
CREATE SEQUENCE IF NOT EXISTS words_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE IF NOT EXISTS USERS(
	ID 		    INTEGER		NOT NULL,
	USERNAME 	TEXT		NOT NULL	UNIQUE,
	PASSWORD 	TEXT		NOT NULL,
	PRIMARY KEY (ID)
);
CREATE SEQUENCE IF NOT EXISTS users_seq INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE IF NOT EXISTS REPETITION(
	WORD_ID 	                INTEGER		            NOT NULL,
	USER_ID		                INTEGER		            NOT NULL,
	E_FACTOR	                DOUBLE PRECISION		NOT NULL DEFAULT 2.5,
	QUALITY		                INTEGER,
	REPEAT_NUMBER	            INTEGER		            NOT NULL DEFAULT 0,
	INTERVAL	                INTEGER		            NOT NULL DEFAULT 1,
	NEXT_REPEAT_DAY             DATE,
	WORD_IS_LEARNED	            BOOLEAN		            NOT NULL DEFAULT FALSE,
	DATE_OF_STUDYING_THE_WORD 	DATE,

	UNIQUE (WORD_ID, USER_ID),

	FOREIGN KEY (WORD_ID) REFERENCES WORDS (ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'a', 'indefinite article', 'used before countable or singular nouns referring to people or things that have not already been mentioned');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'abandon', 'verb', 'abandon somebody to leave someone, especially someone you are responsible for, with no intention of returning');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'abandoned', 'adjective', 'left and not wanted, not used, or no longer needed');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'ability', 'noun', '[singular] ability to do something the fact that someone or something is able to do something');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'able', 'adjective', 'able to do something (used as a modal verb) to have the skill, intelligence, opportunity, etc. needed to do something');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'about', 'adverb', 'a little more or less than; a little before or after');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'above', 'preposition', 'at or to a higher place or position than something or someone');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'abroad', 'adverb', 'in or to a foreign country');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'absence', 'noun', '[uncountable, countable] the fact of someone being away from a place where they are usually expected to be; the occasion or period of time when someone is away');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'absent', 'adjective', 'absent (from something) not in a place because of illness, etc.');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'absolute', 'adjective', 'total and complete');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'absolutely', 'adverb', 'used to emphasize that something is completely true');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'absorb', 'verb', 'to take in a liquid, gas, or other substance from the surface or space around');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'abuse', 'noun', '[uncountable, singular] the use of something in a way that is wrong or harmful');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'academic', 'adjective', '[usually before noun] connected with education, especially studying in colleges and universities');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accent', 'noun', '[countable, uncountable] a way of pronouncing the words of a language that shows which country, area, or social class a person comes from');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accept', 'verb', '[intransitive, transitive] to willingly take something that is offered; to say “yes” to an offer, invitation, etc.');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'acceptable', 'adjective', 'agreed or approved of by most people in a society');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'access', 'noun', 'a way of entering or reaching a place');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accident', 'noun', '[countable] an unpleasant event, especially in a vehicle, that happens unexpectedly and causes injury or damage');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accidental', 'adjective', 'happening by chance; not planned');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accompany', 'verb', 'accompany somebody (formal) to travel or go somewhere with someone');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'according to', 'preposition', 'as stated or reported by someone or something');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'account', 'noun', '(abbreviation acct.) an arrangement that someone has with a bank, etc. to keep money there, take some out, etc.');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accurate', 'adjective', 'correct and true in every detail');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'accuse', 'verb', 'to say that someone has done something wrong or is guilty of something');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'achieve', 'verb', '[transitive] achieve something to succeed in reaching a particular goal, status, or standard, especially by making an effort for a long time');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'achievement', 'noun', '[countable] a thing that someone has done successfully, especially using their own effort and skill');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'acid', 'noun', 'a chemical, usually a liquid, that contains hydrogen and has a pH of less than seven. The hydrogen can be replaced by a metal to form a salt. Acids are usually sour and can often burn holes in or damage things they touch');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'acknowledge', 'verb', 'to accept that something is true');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'acquire', 'verb', 'acquire something to obtain something by buying or being given it');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'across', 'adverb', 'from one side to the other side');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'act', 'noun', '[countable] a particular thing that someone does');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'action', 'noun', '[uncountable] the process of doing something in order to make something happen or to deal with a situation');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'active', 'adjective', 'always busy doing things, especially physical activities');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'activist', 'noun', 'a person who works to achieve political or social change, especially as a member of an organization with particular aims');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'activity', 'noun', '[uncountable] a situation in which something is happening or a lot of things are being done');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'actor', 'noun', 'a person who performs on the stage, on television, or in movies, especially as a profession');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'actress', 'noun', 'a woman who performs on the stage, on television, or in movies, especially as a profession');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'actual', 'adjective', 'used to emphasize something that is real or exists in fact');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'actually', 'adverb', 'used in speaking to emphasize a fact or a comment, or that something is really true');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'ad', 'noun', 'We put an ad in the local paper');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'adapt', 'verb', '[transitive] to change something in order to make it suitable for a new use or situation');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'add', 'verb', '[transitive] to put something together with something else so as to increase the size, number, amount, etc.');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'addition', 'noun', '[uncountable] the process of adding two or more numbers together to find their total');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'additional', 'adjective', 'more than was first mentioned or is usual');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'address', 'noun', '[countable] details of where someone lives or works and where letters, etc. can be sent');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'add up', 'phrasal verb', '(especially in negative sentences) to seem reasonable; to make sense');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'adequate', 'adjective', 'enough in quantity, or good enough in quality, for a particular purpose or need');
INSERT INTO words (id, word, word_type, description) VALUES (nextval('words_seq'), 'adjust', 'verb', '[transitive] to change something slightly to make it more suitable for a new set of conditions or to make it work better');
