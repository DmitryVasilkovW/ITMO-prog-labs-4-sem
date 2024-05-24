create table owners
(
    id serial primary key not null,
    name text not null,
    birthdate date not null,
    email text not null,
    password text not null,
    roles int not null
);

create table cats
(
    id serial primary key not null,
    name text not null,
    birthdate date not null,
    color text not null check ( color in (
                                          'white', 'black', 'red', 'blue', 'green', 'yellow', 'purple', 'orange', 'pink', 'brown',
                                          'gray', 'cyan', 'magenta', 'maroon', 'lime', 'olive', 'navy', 'teal', 'silver', 'gold',
                                          'beige', 'indigo', 'violet', 'ivory', 'turquoise', 'lavender', 'peach', 'salmon', 'plum', 'tan',
                                          'aqua', 'fuchsia', 'khaki', 'coral', 'amber', 'chocolate', 'tomato', 'crimson', 'emerald', 'pearl',
                                          'slate', 'ruby', 'chartreuse', 'sapphire', 'periwinkle', 'azure', 'brass', 'bronze', 'copper', 'zinc',
                                          'quartz', 'obsidian', 'malachite', 'jade', 'garnet', 'a', 'b', 'tmp'
        )),
    breed text not null,
    owner_id int not null,
    foreign key (owner_id) references owners(id)
);

create table owners_cats
(
    cat_id int not null,
    owner_id int not null,
    foreign key (owner_id) references owners(id),
    foreign key (cat_id) references cats(id)
);

create table cats_friends
(
    cat_id int not null,
    friend_id int not null,
    primary key (cat_id, friend_id),
    foreign key (cat_id) references cats(id),
    foreign key (friend_id) references cats(id)
);