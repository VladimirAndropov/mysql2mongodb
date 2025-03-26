db.tags.aggregate([
    // {$match : {"movie.title" : "Willy Wonka and the Chocolate Factory (1971)"}},
    // {$unwind : '$movie'},
    {$project : {'movie' : '$movie', _id:0 }}
])

db.tags.aggregate([
    {$match : {"movie.title" : "Willy Wonka and the Chocolate Factory (1971)"}},
    // {$unwind : '$movie'},
    {$project : {'movie' : '$movie.title', _id:0 }}
])

db.tags.aggregate([
    //  {$match : {"tag" : "narrated"}},
    // {$unwind : '$movie'},
    //{$project : {'movie' : '$movie.title', _id:0 }}
    {$group : {
            _id : { 'tag' : '$tag'},
            value : {$sum : 1}
        }},
]);