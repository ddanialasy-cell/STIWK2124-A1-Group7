INSERT IGNORE INTO publishers (publisherid, publisher_name, publisher_contact)
VALUES (1, 'Unknown Publisher', 'Unknown Contact')
ON DUPLICATE KEY UPDATE publisher_name=VALUES(publisher_name), 
                        publisher_contact=VALUES(publisher_contact);