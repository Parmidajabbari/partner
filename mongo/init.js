db.auth('root', 'a4905b34ddbb73573302f1b719440989')
db = db.getSiblingDB('partnerdb')
db.createUser({
    user: 'root',
    pwd: 'a4905b34ddbb73573302f1b719440989',
    roles: [
        {
            role: 'readWrite',
            db: 'partnerdb',
        }
    ]
});
