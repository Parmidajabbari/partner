db.auth('root', 'a4905b34ddbb73573302f1b719440989')
db = db.getSiblingDB('partnerDB')
db.createUser({
    user: 'partner',
    pwd: 'a27b3f0592bbb92fe2fcc18c46b41b01',
    roles: [
        {
            role: 'readWrite',
            db: 'partnerDB',
        }
    ]
});
