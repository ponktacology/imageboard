import './App.css';
import {useEffect, useState} from "react";

function CommentRow({comment}) {
    return (<div className="comment">
        <p>{comment.author.id}</p>
        <a>{comment.content.text}</a>
    </div>)
}

function CreateCommentRow({board, thread, onCommentCreated}) {
    const [uploading, setUploading] = useState(false)
    const [error, setError] = useState(null)
    const [content, setContent] = useState('')

    const submit = async () => {
        setUploading(true)
        setError(null)

        let json = JSON.stringify({
            "content": {
                text: content
            }
        })

        try {
            const response = await fetch(`http://localhost:8080/posts/put/${board.id}/${thread.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: json,
            })
            if (!response.ok) {
                throw new Error(await response.text());
            }
            const data = await response.json();
            setContent('')
            onCommentCreated(data)
        } catch (err) {
            setError(err.message);
        } finally {
            setUploading(false);
        }
    }

    return <div className="createComment">
        {uploading && <p>Uploading...</p>}
        {error && <p>Error: {error}</p>}

        <h1>Add comment</h1>
        <form>
            <label>Content: <input
                type="text"
                value={content}
                onChange={(e) => setContent(e.target.value)}
                required
            /></label>
            <button onClick={submit} type="submit" disabled={uploading}>
                {uploading ? 'Submitting...' : 'Submit'}
            </button>
        </form>

    </div>
}

function ThreadRow({board, thread}) {
    const [showComments, setShowComments] = useState(false);
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchComments = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await fetch(`http://localhost:8080/posts/get/${board.id}/${thread.id}`);
            if (response.ok) {
                const data = await response.json();
                setComments(data);
            }

        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const addCommentToList = (newComment) => {
        setComments(prevComments => [...prevComments, newComment]); // Prepend the new thread
        if (!showComments) {
            setShowComments(true)
            fetchComments()
        }
    };

    const toggleComments = async () => {
        if (!showComments) {
            await fetchComments();
        }
        setShowComments(prevState => !prevState);
    };

    return (
        <div className="thread">
            <h1>{thread.title}</h1>
            <p>{thread.author.id}</p>
            <a>{thread.content.text}</a>

            <button onClick={toggleComments}>
                {showComments ? 'Hide Comments' : 'Show Comments'}
            </button>

            {showComments && (
                <div className="comments">
                    {loading && <p>Loading...</p>}
                    {error && <p>Error: {error}</p>}
                    {comments.length > 0 ? (
                        <ul>
                            {comments.map((comment, index) => (
                                <CommentRow key={comment.id} comment={comment}/>
                            ))}
                        </ul>
                    ) : (
                        !loading && <p>No comments yet.</p>
                    )}
                </div>
            )}

            <CreateCommentRow key={thread.id} thread={thread} board={board} onCommentCreated={addCommentToList}/>
        </div>
    );
}

function CreateThreadRow({board, onThreadCreated}) {
    const [uploading, setUploading] = useState(false)
    const [error, setError] = useState(null)
    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')

    const submit = async () => {
        setUploading(true)
        setError(null)

        let json = JSON.stringify({
            "title": title,
            "content": {
                text: content
            }
        })

        try {
            const response = await fetch(`http://localhost:8080/threads/put/${board.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: json,
            })
            if (!response.ok) {
                throw new Error(await response.text());
            }
            const data = await response.json();
            onThreadCreated(data)
        } catch (err) {
            setError(err.message);
        } finally {
            setUploading(false);
        }
    }


    return <div className="createThread">
        {uploading && <p>Uploading...</p>}
        {error && <p>Error: {error}</p>}

        <h1>Create a Thread</h1>
        <form>
            <label>Title: <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
            /></label>
            <label>Content: <input
                type="text"
                value={content}
                onChange={(e) => setContent(e.target.value)}
                required
            /></label>
            <button onClick={submit} type="submit" disabled={uploading}>
                {uploading ? 'Submitting...' : 'Submit'}
            </button>
        </form>

    </div>
}

function Board({board}) {
    const [threads, setThreads] = useState([])
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchThreads = async () => {
        setLoading(true)
        setError(null)

        try {
            const response = await fetch(`http://localhost:8080/threads/all/${board.id}`);
            if (response.ok) {
                const data = await response.json();
                setThreads(data);
            }
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    const addThreadToList = (newThread) => {
        setThreads(prevThreads => [newThread, ...prevThreads]); // Prepend the new thread
    };

    useEffect(() => {
        fetchThreads()
    }, []);

    return (
        <div className="board">
            <CreateThreadRow board={board} onThreadCreated={addThreadToList}/>
            {error && <p>Error: {error}</p>}
            {loading && <p>Loading...</p>}
            {threads.length > 0 ? (
                <ul>
                    {threads.map((comment, index) => (
                        <ThreadRow key={comment.id} thread={comment} board={board}/>
                    ))}
                </ul>
            ) : (
                !loading && <p>No threads.</p>
            )}
        </div>
    );
}

function NavBar() {
    return (
        <nav className="navbar">
            <div className="navbar-logo">
                <a href="/">MyApp</a>
            </div>
            <ul className="navbar-links">
                <li><a href="/">Home</a></li>
                <li><a href="/about">About</a></li>
                <li><a href="/services">Services</a></li>
                <li><a href="/contact">Contact</a></li>
            </ul>
        </nav>
    );
}

const BOARD =
    {
        id: "test"
    }


function App() {
    return <div>
        <NavBar/>
        <Board board={BOARD}/>
    </div>
}

export default App;
